package com.example.sadok.commitreader.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sadok.commitreader.R
import com.example.sadok.commitreader.databinding.CommitListFragmentBinding

class CommitListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: CommitListFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.commit_list_fragment, container, false)

        val application = requireNotNull(this.activity).application


        val viewModelFactory = CommitListViewModelFactory(application)

        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(CommitListViewModel::class.java)

        binding.viewModel = viewModel

        binding.lifecycleOwner = this
        val adapter = CommitAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.commitList.observe(viewLifecycleOwner, Observer {
            it?.apply {
                adapter.submitList(it)
            }
        })
        return binding.root
    }


}
