package com.lkby.tracker.data.mapper

import com.google.firebase.auth.FirebaseUser
import com.lkby.tracker.data.remote.auth.model.FirebaseAuthResult
import com.lkby.tracker.data.remote.user.model.UserDto
import com.lkby.tracker.domain.model.User

internal fun FirebaseAuthResult.toUserDto(): UserDto {
    return UserDto(
        uid = uid,
        name = displayName,
        email = email,
        photoUrl = photoUrl
    )
}

internal fun UserDto.toDomain(): User {
    return User(
        uid = uid,
        name = name,
        email = email,
        photoUrl = photoUrl
    )
}

internal fun FirebaseUser.toDomain(): User {
    return User(
        uid = uid,
        name = displayName,
        email = email,
        photoUrl = photoUrl?.toString()
    )
}