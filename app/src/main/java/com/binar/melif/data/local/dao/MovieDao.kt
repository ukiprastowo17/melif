package com.binar.melif.data.local.dao

import androidx.room.*
import com.binar.melif.data.local.entity.FavoriteMovieEntity


@Dao
interface MovieDao {
    @Query("SELECT * FROM favoritemovieentity ORDER BY date_added DESC")
    suspend fun getAllMovies(): List<FavoriteMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieEntity: FavoriteMovieEntity) : Long

    @Delete
    suspend fun deleteMovie(movieEntity: FavoriteMovieEntity)

    @Query("SELECT EXISTS (SELECT * FROM favoritemovieentity WHERE id=:id)")
    suspend fun movieExists(id: Int): Boolean
}