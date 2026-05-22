package com.example.mobileprogrammingproject.model.data.repository.mappers

import com.example.mobileprogrammingproject.model.data.local.dao.SongDao
import com.example.mobileprogrammingproject.model.data.local.entity.SongEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SongRepository @Inject constructor(
    private val songDao: SongDao
) {
    fun getAllSongs(): Flow<List<SongEntity>> = songDao.getAllSongs()

    fun getFilteredSongs(query: String): Flow<List<SongEntity>> = songDao.getFilteredSongs(query)

    suspend fun insertSong(song: SongEntity) = songDao.insertSong(song)

    suspend fun insertAllSongs(songs: List<SongEntity>) = songDao.insertAllSongs(songs)

    suspend fun deleteSong(song: SongEntity) = songDao.deleteSong(song)

    suspend fun updateSong(song: SongEntity) = songDao.updateSong(song)
}