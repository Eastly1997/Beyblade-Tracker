package com.lkby.tracker.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lkby.tracker.domain.usecase.GetCurrentUserUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class SplashViewModel(
    private val getCurrentUserUseCase: GetCurrentUserUseCase
): ViewModel() {
    private val _effect = Channel<SplashEffect>()
    val effect = _effect.receiveAsFlow()

    companion object {
        private const val MIN_SPLASH_DURATION = 2000L
    }

    init {
        checkSession()
    }

    private fun checkSession() {
        viewModelScope.launch {
            val startTime = System.currentTimeMillis()

            val user = getCurrentUserUseCase()

            val elapsed = System.currentTimeMillis() - startTime
            val remaining = MIN_SPLASH_DURATION - elapsed

            if (remaining > 0) {
                delay(remaining)
            }

            if(user != null) {
                _effect.send(SplashEffect.NavigateToHome)
            } else {
                _effect.send(SplashEffect.NavigateToAuth)
            }
        }
    }
}