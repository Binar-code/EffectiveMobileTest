package com.example.effectivemobiletest.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobiletest.databinding.FragmentHomeBinding
import com.example.effectivemobiletest.ui.home.feed.FeedItem
import com.example.effectivemobiletest.ui.home.feed.HomeAdapter
import com.example.effectivemobiletest.ui.home.feed.toCourseUi
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    // TODO: ликвид гласс
    // TODO: лоадер в первую загрузку
    // TODO: pull to refresh
    // TODO: функционал избранного

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val vm: HomeViewModel by viewModel()
    private val adapter by lazy {
        HomeAdapter(
            onFavClick = { item -> vm.onFavClick(item.id) }
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

        initRecycler()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.uiState.collect { state ->
                    binding.feed.post {
                        adapter.submitList(decorate(state))
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
        return state.items.map { item -> item.toCourseUi() }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}