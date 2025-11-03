package com.example.effectivemobiletest.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.result.Result
import com.example.domain.usecase.ObserveUseCase
import com.example.domain.usecase.RefreshUseCase
import com.example.domain.usecase.UpdateCacheUseCase
import com.example.domain.usecase.UpdateFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val observe: ObserveUseCase,
    private val refresh: RefreshUseCase,
    private val updateCache: UpdateCacheUseCase,
    private val updateFav: UpdateFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    @Volatile private var isRefreshing = false

    init {
        observe()
            .distinctUntilChanged()
            .onEach { list ->
                _uiState.update { it.copy(items = list, isLoading = false) }
            }
            .catch { e ->
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
            .launchIn(viewModelScope)

        refreshNow()
    }

    private fun refreshNow() {
        if (isRefreshing) return
        isRefreshing = true

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                when (val res = refresh()) {
                    is Result.Success -> {
                        updateCache(res.data)
                    }
                    is Result.Error.HttpError -> {
                        _uiState.update { it.copy(error = res.msg) }
                    }
                    is Result.Error.Unknown -> {
                        _uiState.update { it.copy(error = "Unknown error") }
                    }
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(error = t.message) }
            } finally {
                isRefreshing = false
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun onFavClick(itemId: Int) {
        var isFav = false
        _uiState.update { state ->
            val newList = state.items.map { item ->
                if (item.id == itemId) {
                    isFav = item.hasLike
                    item.copy(hasLike = !item.hasLike)
                }
                else item
            }
            state.copy(items = newList)
        }

        viewModelScope.launch(Dispatchers.IO) {
            updateFav(itemId, !isFav)
        }
    }

    fun setMode(mode: ScreenMode) {
        _uiState.update { it.copy(mode = mode) }
    }

    fun sortByDate() {
        if (!uiState.value.sortDesc) {
            _uiState.update { state ->
                state.copy(
                    items = state.items.sortedByDescending { it.startDate },
                    sortDesc = true
                )
            }
        } else {
            _uiState.update { state ->
                state.copy(
                    items = state.items.sortedBy { it.startDate },
                    sortDesc = false
                )
            }
        }
    }
}
