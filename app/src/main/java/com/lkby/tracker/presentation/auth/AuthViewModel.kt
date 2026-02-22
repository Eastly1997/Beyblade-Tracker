package com.lkby.tracker.presentation.auth

import androidx.lifecycle.ViewModel
import com.lkby.tracker.domain.usecase.SignInWithGoogleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase
): ViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()

    private val _effect = Channel<AuthEffect>()
    val effect = _effect.receiveAsFlow()

    fun handleIntent(intent: AuthIntent) {
        when (intent) {
            is AuthIntent.SignInWithGoogleClicked -> {
                launchGoogleSignIn()
            }
            is AuthIntent.GoogleTokenReceived -> {
                signInWithGoogle(intent.idToken)
            }
            }
    }

    private fun launchGoogleSignIn() {
        viewModelScope.launch {
            _effect.send(AuthEffect.LaunchGoogleSignIn)
        }
    }

    private fun signInWithGoogle(idToken: String) {
        if(idToken.isBlank()) {
            viewModelScope.launch {
                _effect.send(AuthEffect.ShowError("Google Sign-In cancelled"))
            }
            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            signInWithGoogleUseCase(idToken)
                .onSuccess {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isSignedIn = true,
                            error = null
                        )
                    }
                    _effect.send(AuthEffect.NavigateToHome)
                }
                .onFailure { error ->
                    val message = error.message ?: "Sign-in failed"
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = message
                        )
                    }
                    _effect.send(AuthEffect.ShowError(message))
                }
        }
    }
}