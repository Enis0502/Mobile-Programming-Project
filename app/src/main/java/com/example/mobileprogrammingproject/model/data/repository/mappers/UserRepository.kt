package com.example.mobileprogrammingproject.model.data.repository.mappers

import com.example.mobileprogrammingproject.model.data.local.dao.UserDao
import com.example.mobileprogrammingproject.model.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    fun getAllUsers(): Flow<List<UserEntity>> {
        return userDao.getAllUsers()
    }

    suspend fun insertUser(user: UserEntity): Long {
        return userDao.insertUser(user)
    }

    suspend fun getUserByEmail(email: String): UserEntity? {
        return userDao.getUserByEmail(email)
    }

    suspend fun login(email: String, password: String): UserEntity? {
        return userDao.login(email, password)
    }
}