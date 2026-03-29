package com.lkby.feature.auth.domain.usecase

import com.lkby.feature.auth.domain.repository.AuthRepository
import com.lkby.feature.auth.domain.repository.model.AuthResult

class SignInWithGoogleUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(idToken: String): Result<AuthResult> {
        return repository.signInWithGoogle(idToken)
    }
}