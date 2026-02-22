package com.lkby.tracker.presentation.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lkby.tracker.R
import com.lkby.tracker.presentation.navigation.Route
import com.lkby.tracker.presentation.ui.loginBackground
import com.lkby.tracker.ui.theme.BeybladeTrackerTheme
import org.koin.androidx.compose.koinViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = koinViewModel()
) {
    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when(effect) {
                SplashEffect.NavigateToHome -> {
                    navController.navigate(Route.Home.value) {
                        popUpTo(Route.Splash.value) { inclusive = true }
                    }
                }

                SplashEffect.NavigateToAuth -> {
                    navController.navigate(Route.Auth.value) {
                        popUpTo(Route.Splash.value) { inclusive = true }
                    }
                }
            }
        }
    }

    SplashScreenContent()
}

@Composable
fun SplashScreenContent() {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.spinner)
    )

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )

    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(800),
        label = "fade"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(loginBackground()),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.alpha(alpha)
        ) {

            LottieAnimation(
                composition = composition,
                progress = progress,
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "BEYBLADE X",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "TRACKER",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF00E5FF)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Preparing your arena...",
                fontSize = 14.sp,
                color = Color.LightGray
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    BeybladeTrackerTheme {
        SplashScreenContent()
    }
}