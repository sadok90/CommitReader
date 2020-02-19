package com.example.sadok.commitreader.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.sadok.commitreader.network.Network
import com.example.sadok.commitreader.db.CommitDatabase
import com.example.sadok.commitreader.db.asDomainModel
import com.example.sadok.commitreader.domain.Commit
import com.example.sadok.commitreader.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class Repository(private val database: CommitDatabase) {

    //private val factory = database.commitDao.getAll().map { it.asDomainModel() }
    //val commits = LivePagedListBuilder(factory,  /* page size */10).build()
    val commits : LiveData<List<Commit>> =
    Transformations.map(database.commitDao.getAll()) {
        it.asDomainModel()
    }
    suspend fun refreshCommits() {
        withContext(Dispatchers.IO) {
            try {
                val commits = Network.retrofitService.getCommits().await()
                database.commitDao.insertAll(*commits.asDatabaseModel())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}