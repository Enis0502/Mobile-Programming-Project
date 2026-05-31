package com.example.mobileprogrammingproject.model.data.repository.mappers

import com.example.mobileprogrammingproject.model.data.local.dao.PlaylistDao
import com.example.mobileprogrammingproject.model.data.local.entity.PlaylistEntity
import com.example.mobileprogrammingproject.model.datasource.network.dto.PlaylistResponseDto
import com.example.mobileprogrammingproject.model.datasource.network.service.DeezerApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlaylistRepository @Inject constructor(
    private val playlistDao: PlaylistDao,
    private val api: DeezerApiService
) {
    suspend fun getTopPlaylists(): PlaylistResponseDto{
        return  api.getTopPlaylists()
    }
    fun getAllPlaylists(): Flow<List<PlaylistEntity>> = playlistDao.getAllPlaylists()

    fun getPlaylistsByUser(userId: Int): Flow<List<PlaylistEntity>> =
        playlistDao.getPlaylistsByUser(userId)

    suspend fun insertPlaylist(playlist: PlaylistEntity) = playlistDao.insertPlaylist(playlist)

    suspend fun updatePlaylist(playlist: PlaylistEntity) = playlistDao.updatePlaylist(playlist)

    suspend fun deletePlaylist(playlist: PlaylistEntity) = playlistDao.deletePlaylist(playlist)
}