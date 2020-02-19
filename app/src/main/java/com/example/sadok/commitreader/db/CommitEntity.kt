package com.example.sadok.commitreader.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sadok.commitreader.domain.Commit

@Entity(tableName = "commit_table")
data class CommitEntity(
    @PrimaryKey
    val id: String,
    val sha: String,
    val message: String,
    val authorName: String,
    val authorAvatar: String
)

fun CommitEntity.asDomainModel(): Commit {
    return Commit(
        id = id,
        sha = sha,
        message = message,
        authorName = authorName,
        authorAvatar = authorAvatar
    )
}

fun List<CommitEntity>.asDomainModel(): List<Commit> {
    return map {
        it.asDomainModel()
    }
}

