package com.example.effectivemobiletest.ui.home

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.databinding.FragmentHomeBinding
import com.example.effectivemobiletest.ui.home.feed.FeedItem
import com.example.effectivemobiletest.ui.home.feed.HomeAdapter
import com.example.effectivemobiletest.ui.home.feed.toCourseUi
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    // TODO: лоадер в первую загрузку
    // TODO: pull to refresh
    // TODO: заглушка для пустого избранного
    // TODO: сортировка
    private val args: HomeFragmentArgs by navArgs()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val vm: HomeViewModel by viewModel()
    private val adapter by lazy {
        HomeAdapter(
            onFavClick = { item ->
                vm.onFavClick(item.id)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentHomeBinding
        .inflate(inflater, container, false)
        .also { _binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (args.mode) {
            ScreenMode.HOME -> {
                vm.setMode(ScreenMode.HOME)
                binding.favLabel.visibility = GONE
                binding.topBar.visibility = VISIBLE
                binding.filter.visibility = VISIBLE
            }

            ScreenMode.FAVORITE -> {
                vm.setMode(ScreenMode.FAVORITE)
                binding.favLabel.visibility = VISIBLE
                binding.topBar.visibility = GONE
                binding.filter.visibility = GONE
            }
        }


        initRecycler()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.uiState.collect { state ->
                    binding.feed.post {
                        adapter.submitList(decorate(state))
                    }

                    binding.filter.setOnClickListener {
                        vm.sortByDate()
                    }
                }
            }
        }

    }

    private fun initRecycler() {
        binding.feed.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator().apply { supportsChangeAnimations = false }
            adapter = this@HomeFragment.adapter
        }
    }

    private fun decorate(state: HomeUiState): List<FeedItem> {
        val source = if (state.mode == ScreenMode.FAVORITE) {
            state.items.filter { it.hasLike }
        } else {
            state.items
        }
        return source.map { it.toCourseUi() }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}