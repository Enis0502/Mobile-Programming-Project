package com.example.mobileprogrammingproject.model.data.di

import android.content.Context
import androidx.room.Room
import com.example.mobileprogrammingproject.model.data.local.dao.PlaylistDao
import com.example.mobileprogrammingproject.model.data.local.dao.PlaylistSongDao
import com.example.mobileprogrammingproject.model.data.local.dao.RecentlyPlayedDao
import com.example.mobileprogrammingproject.model.data.local.dao.SongDao
import com.example.mobileprogrammingproject.model.data.local.dao.UserDao
import com.example.mobileprogrammingproject.model.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mobileprogrammingproject.model.data.local.util.DatabaseSeeder

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    DatabaseSeeder.songs.forEach { song ->
                        db.execSQL(
                            "INSERT INTO songs (id, title, artist, duration) VALUES (${song.id}, '${song.title.replace("'", "''")}', '${song.artist.replace("'", "''")}', '${song.duration}')"
                        )
                    }
                }
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideSongDao(database: AppDatabase): SongDao {
        return database.songDao()
    }

    @Provides
    @Singleton
    fun providePlaylistDao(database: AppDatabase): PlaylistDao {
        return database.playlistDao()
    }

    @Provides
    @Singleton
    fun providePlaylistSongDao(database: AppDatabase): PlaylistSongDao {
        return database.playlistSongDao()
    }

    @Provides
    @Singleton
    fun provideRecentlyPlayedDao(database: AppDatabase): RecentlyPlayedDao {
        return database.recentlyPlayedDao()
    }
}