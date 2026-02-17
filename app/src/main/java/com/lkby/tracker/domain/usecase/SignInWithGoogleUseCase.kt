package com.lkby.tracker.domain.usecase

import com.lkby.tracker.domain.repository.AuthRepository

class SignInWithGoogleUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(idToken: String): Result<String> {
        return repository.signInWithGoogle(idToken)
    }
}