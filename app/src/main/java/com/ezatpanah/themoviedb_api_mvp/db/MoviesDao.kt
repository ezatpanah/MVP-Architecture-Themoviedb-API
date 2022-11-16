package com.ezatpanah.themoviedb_api_mvp.db

import androidx.room.*
import com.ezatpanah.themoviedb_api_mvp.utils.Constants.MOVIES_TABLE

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertMovie(entity: MoviesEntity)

    @Delete
    suspend fun deleteMovie(entity: MoviesEntity)

    @Query("SELECT * From ${MOVIES_TABLE}")
    fun getAllMovies() : MutableList<MoviesEntity>

    @Query("SELECT EXISTS (SELECT 1 FROM ${MOVIES_TABLE} WHERE id = :id)")
    suspend fun existMovie(id:Int) : Boolean


}