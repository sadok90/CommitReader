/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.sadok.commitreader

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.sadok.commitreader.db.CommitDao
import com.example.sadok.commitreader.db.CommitDatabase
import com.example.sadok.commitreader.db.CommitEntity
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class CommitDatabaseTest {

    private lateinit var commitDao: CommitDao
    private lateinit var db: CommitDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, CommitDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        commitDao = db.commitDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetSong() {
        val commit = CommitEntity(
            id = "aaa",
            sha = "ZZAADD",
            message = "first commit",
            authorName = "Sadok",
            authorAvatar = "")
        commitDao.insert(commit)
        val commit1 = commitDao.get("aaa")
        assertNotNull(commit1)
        assertEquals(commit1, commit)
    }


}

