package com.example.sadok.commitreader.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CommitListViewModelFactory(
        private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommitListViewModel::class.java)) {
            return CommitListViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}