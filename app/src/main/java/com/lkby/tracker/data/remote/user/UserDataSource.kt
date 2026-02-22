package com.lkby.tracker.data.remote.user

import com.lkby.tracker.data.remote.user.model.UserDto

internal interface UserDataSource {
    suspend fun createUser(user: UserDto)

    suspend fun getUser(uid: String): UserDto?
}