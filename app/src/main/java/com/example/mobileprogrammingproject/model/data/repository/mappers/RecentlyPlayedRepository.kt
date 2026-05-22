package com.example.mobileprogrammingproject.model.data.repository.mappers

import com.example.mobileprogrammingproject.model.data.local.dao.RecentlyPlayedDao
import com.example.mobileprogrammingproject.model.data.local.entity.RecentlyPlayedEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecentlyPlayedRepository @Inject constructor(
    private val recentlyPlayedDao: RecentlyPlayedDao
) {
    fun getRecentlyPlayedByUser(userId: Int): Flow<List<RecentlyPlayedEntity>> =
        recentlyPlayedDao.getRecentlyPlayedByUser(userId)

    suspend fun insertRecentlyPlayed(userId: Int, songId: Int) =
        recentlyPlayedDao.insertRecentlyPlayed(
            RecentlyPlayedEntity(userId = userId, songId = songId)
        )

    suspend fun deleteRecentlyPlayed(recentlyPlayed: RecentlyPlayedEntity) =
        recentlyPlayedDao.deleteRecentlyPlayed(recentlyPlayed)

    suspend fun clearRecentlyPlayed(userId: Int) =
        recentlyPlayedDao.clearRecentlyPlayed(userId)
}