package com.ezatpanah.themoviedb_api_mvp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MoviesEntity::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract  fun moviesDoa() : MoviesDao
}