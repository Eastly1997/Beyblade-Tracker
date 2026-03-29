package com.lkby.feature.auth.domain.usecase

import com.lkby.feature.auth.domain.repository.AuthRepository
import com.lkby.feature.auth.domain.repository.model.User

class GetCurrentUserUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke(): User? {
        return repository.getCurrentUser()
    }
}