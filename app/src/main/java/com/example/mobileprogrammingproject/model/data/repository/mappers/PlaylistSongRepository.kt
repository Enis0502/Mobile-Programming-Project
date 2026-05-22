package com.example.mobileprogrammingproject.model.data.repository.mappers

import com.example.mobileprogrammingproject.model.data.local.dao.PlaylistSongDao
import com.example.mobileprogrammingproject.model.data.local.entity.PlaylistSongCrossRef
import com.example.mobileprogrammingproject.model.data.local.entity.PlaylistWithSongs
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlaylistSongRepository @Inject constructor(
    private val playlistSongDao: PlaylistSongDao
) {
    fun getPlaylistWithSongs(playlistId: Int): Flow<PlaylistWithSongs> =
        playlistSongDao.getPlaylistWithSongs(playlistId)

    fun getAllPlaylistsWithSongs(): Flow<List<PlaylistWithSongs>> =
        playlistSongDao.getAllPlaylistsWithSongs()

    suspend fun addSongToPlaylist(playlistId: Int, songId: Int) =
        playlistSongDao.addSongToPlaylist(PlaylistSongCrossRef(playlistId, songId))

    suspend fun removeSongFromPlaylist(playlistId: Int, songId: Int) =
        playlistSongDao.removeSongFromPlaylist(PlaylistSongCrossRef(playlistId, songId))
}