package com.example.mobileprogrammingproject.model.data.local.dao

import androidx.room.*
import com.example.mobileprogrammingproject.model.data.local.entity.RecentlyPlayedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentlyPlayedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecentlyPlayed(recentlyPlayed: RecentlyPlayedEntity)

    @Delete
    suspend fun deleteRecentlyPlayed(recentlyPlayed: RecentlyPlayedEntity)

    @Query("SELECT * FROM recently_played WHERE userId = :userId ORDER BY playedAt DESC")
    fun getRecentlyPlayedByUser(userId: Int): Flow<List<RecentlyPlayedEntity>>

    @Query("DELETE FROM recently_played WHERE userId = :userId")
    suspend fun clearRecentlyPlayed(userId: Int)
}