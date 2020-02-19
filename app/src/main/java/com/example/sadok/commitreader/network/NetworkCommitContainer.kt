package com.example.sadok.commitreader.network

import com.example.sadok.commitreader.db.CommitEntity
import com.squareup.moshi.Json

data class Container(
        @Json(name = "node_id")
        val nodeId: String,
        val sha: String,
        val commit: NetworkCommit,
        val author: NetworkAuthor?) {

    fun asDatabaseModel(): CommitEntity = CommitEntity(
        id = nodeId,
        sha = sha,
        message = commit.message,
        authorName = commit.author.name,
        authorAvatar = author?.avatar?: "")
}

data class NetworkCommit(
    val author: NetworkCommitAuthor,
    val message: String
)

data class NetworkAuthor(
    @Json(name = "avatar_url")
    val avatar: String
)

data class NetworkCommitAuthor(
    val name: String
)


fun List<Container>.asDatabaseModel(): Array<CommitEntity> {
    return map {
        it.asDatabaseModel()
    }.toTypedArray()
}
