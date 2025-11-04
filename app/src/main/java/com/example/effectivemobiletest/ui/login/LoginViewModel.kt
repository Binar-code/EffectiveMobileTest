package com.example.effectivemobiletest.ui.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(text: String) {
        _uiState.value = _uiState.value.copy(email = text)
        validate()
    }

    fun onPasswordChange(text: String) {
        _uiState.value = _uiState.value.copy(password = text)
        validate()
    }

    private fun validate() {
        val s = _uiState.value
        val emailValid = s.email.trim().isNotEmpty() &&
            Patterns.EMAIL_ADDRESS.matcher(s.email.trim()).matches()
        val passValid = s.password.trim().isNotEmpty()
        _uiState.value = s.copy(loginButtonEnabled = emailValid && passValid)
    }
}
