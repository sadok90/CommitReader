package com.example.sadok.commitreader.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.sadok.commitreader.db.CommitDatabase
import com.example.sadok.commitreader.domain.Commit
import com.example.sadok.commitreader.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


class CommitListViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val database = CommitDatabase.getInstance(application)
    private val repository = Repository(database)


    init {
        refreshCommits()
    }

    val commitList: LiveData<List<Commit>> = repository.commits

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun refreshCommits() {
        viewModelScope.launch {
            repository.refreshCommits()
        }
    }



}
