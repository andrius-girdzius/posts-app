package com.example.postsapp.ui.postList

import PostListAdapter
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postsapp.databinding.ListFragmentBinding
import com.example.postsapp.utils.ApiException
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PostListFragment : Fragment() {
    private lateinit var binding: ListFragmentBinding
    private val viewModel: PostListViewModel by viewModels()
    private lateinit var adapter: PostListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PostListAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.posts.observe(viewLifecycleOwner) {
            adapter.setPosts(it)
            it.forEach { post ->
                fetchAndSaveUser(post.userId)
            }
        }

        val refreshLayout = binding.refreshLayout
        refreshLayout.setOnRefreshListener {
            fetchPosts()
        }
    }

    private fun fetchPosts() {
        try {
            viewModel.viewModelScope.launch {
                viewModel.repository.getPosts()
            }
        } catch (e: Exception) {
            when (e) {
                is ApiException -> showErrorDialog()
                else -> {}
            }
        }
    }

    private fun fetchAndSaveUser(id: Int) {
        viewModel.viewModelScope.launch {
            viewModel.repository.getUser(id).observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) adapter.addUser(it[0])
            }
        }
    }

    private fun showErrorDialog() {
        val alertDialog: AlertDialog = AlertDialog.Builder(this.requireContext()).create()
        // TODO
        alertDialog.show()
    }
}