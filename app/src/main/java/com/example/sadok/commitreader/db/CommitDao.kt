package com.example.sadok.commitreader.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CommitDao {

    @Query("SELECT * from commit_table WHERE id = :key")
    fun get(key: String): CommitEntity?

    @Query("SELECT * from commit_table")
    fun getAll(): LiveData<List<CommitEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg commits: CommitEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(commit: CommitEntity)
}