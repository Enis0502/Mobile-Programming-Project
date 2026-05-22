package com.example.mobileprogrammingproject.model.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mobileprogrammingproject.model.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao{
    @Insert
    suspend fun insertUser(user: UserEntity): Long

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Query("SELECT * FROM users WHERE email= :email AND password = :password LIMIT 1")
    suspend fun login(email: String, password: String): UserEntity?
}