package com.example.sadok.commitreader.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sadok.commitreader.databinding.CommitItemBinding
import com.example.sadok.commitreader.domain.Commit

class CommitAdapter : ListAdapter<Commit,
        CommitAdapter.ViewHolder>(CommitDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if(item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: CommitItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Commit) {
            binding.commit = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CommitItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}


class CommitDiffCallback : DiffUtil.ItemCallback<Commit>() {
    override fun areItemsTheSame(oldItem: Commit, newItem: Commit): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Commit, newItem: Commit): Boolean {
        return oldItem == newItem
    }
}
