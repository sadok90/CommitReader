package com.example.sadok.commitreader.domain

data class Commit(val id: String, val sha: String, val message: String, val authorName: String, val authorAvatar: String)
