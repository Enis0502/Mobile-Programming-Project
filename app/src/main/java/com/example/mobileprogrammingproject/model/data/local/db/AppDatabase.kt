package com.example.mobileprogrammingproject.model.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobileprogrammingproject.model.data.local.dao.PlaylistDao
import com.example.mobileprogrammingproject.model.data.local.dao.PlaylistSongDao
import com.example.mobileprogrammingproject.model.data.local.dao.RecentlyPlayedDao
import com.example.mobileprogrammingproject.model.data.local.dao.SongDao
import com.example.mobileprogrammingproject.model.data.local.dao.UserDao
import com.example.mobileprogrammingproject.model.data.local.entity.PlaylistEntity
import com.example.mobileprogrammingproject.model.data.local.entity.PlaylistSongCrossRef
import com.example.mobileprogrammingproject.model.data.local.entity.RecentlyPlayedEntity
import com.example.mobileprogrammingproject.model.data.local.entity.SongEntity
import com.example.mobileprogrammingproject.model.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        SongEntity::class,
        PlaylistEntity::class,
        PlaylistSongCrossRef::class,
        RecentlyPlayedEntity::class
    ],
    version = 5,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase(){
    abstract fun userDao(): UserDao

    abstract fun songDao(): SongDao

    abstract fun playlistDao(): PlaylistDao

    abstract fun playlistSongDao(): PlaylistSongDao

    abstract fun recentlyPlayedDao(): RecentlyPlayedDao
}