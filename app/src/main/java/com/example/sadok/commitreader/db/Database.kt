package com.example.sadok.commitreader.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [CommitEntity::class], version = 1, exportSchema = false)
abstract class CommitDatabase: RoomDatabase() {
    abstract val commitDao: CommitDao

    companion object {

        @Volatile
        private var INSTANCE: CommitDatabase? = null


        fun getInstance(context: Context): CommitDatabase {

            synchronized(this) {
                 var instance = INSTANCE
                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CommitDatabase::class.java,
                        "commit_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}