package com.lkby.tracker.domain.usecase

import com.lkby.tracker.domain.model.User
import com.lkby.tracker.domain.repository.AuthRepository

class GetCurrentUserUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke(): User? {
        return repository.getCurrentUser()
    }
}