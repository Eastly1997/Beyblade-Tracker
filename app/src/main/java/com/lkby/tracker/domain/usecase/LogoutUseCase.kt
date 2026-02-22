package com.lkby.tracker.domain.usecase

import com.lkby.tracker.domain.repository.AuthRepository

class LogoutUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke() {
        repository.logout()
    }
}