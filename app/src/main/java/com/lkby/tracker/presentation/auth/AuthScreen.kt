package com.lkby.tracker.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.navigation.NavController
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.lkby.tracker.R
import com.lkby.tracker.presentation.navigation.Route
import com.lkby.tracker.presentation.ui.loginBackground
import com.lkby.tracker.ui.theme.BeybladeTrackerTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val webClientId = stringResource(R.string.default_web_client_id)
    val credentialManager = remember {
        CredentialManager.create(context)
    }

    val googleIdOption = remember {
        GetGoogleIdOption.Builder()
            .setServerClientId(webClientId)
            .setFilterByAuthorizedAccounts(false)
            .build()
    }

    val request = remember {
        GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
    }

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when(effect) {
                AuthEffect.LaunchGoogleSignIn -> {
                    try {
                        val result = credentialManager.getCredential(
                            context = context,
                            request = request
                        )

                        val credential = result.credential
                        if (credential is CustomCredential &&
                            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
                        ) {

                            val googleCredential =
                                GoogleIdTokenCredential.createFrom(
                                    credential.data
                                )

                            val idToken = googleCredential.idToken

                            viewModel.handleIntent(
                                AuthIntent.GoogleTokenReceived(idToken)
                            )
                        }

                    } catch (e: Exception) {
                        if (e is GetCredentialCancellationException) {
                            return@collect
                        }
                        viewModel.handleIntent(
                            AuthIntent.GoogleTokenReceived("")
                        )
                    }
                }
                AuthEffect.NavigateToHome -> {
                    navController.navigate(Route.Home.value) {
                        popUpTo(Route.Auth.value) {
                            inclusive = true
                        }
                    }
                }
                is AuthEffect.ShowError -> {
                    Toast.makeText(
                        context,
                        effect.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    AuthScreenContent(
        state = state,
        onGoogleClick = {
            viewModel.handleIntent(AuthIntent.SignInWithGoogleClicked)
        }
    )

}

@Composable
private fun AuthScreenContent(
    state: AuthState,
    onGoogleClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(loginBackground())
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Header()

            GoogleButton(
                enabled = !state.isLoading,
                onClick = onGoogleClick
            )
        }

        if (state.isLoading) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
                    .clickable(enabled = false) {}, // block clicks
                contentAlignment = Alignment.Center
            ) {

                androidx.compose.material3.CircularProgressIndicator(
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun GoogleButton(
    enabled: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(bottom = 50.dp)
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(4.dp))
            .clickable(
                enabled = enabled,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick()
            }
    ) {

        Image(
            painter = painterResource(R.drawable.google_signin_button),
            contentDescription = "Continue with Google",
            modifier = Modifier.fillMaxSize(),
            alpha = if (enabled) 1f else 0.5f
        )

    }
}
@Composable
private fun Header() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "BEYBLADE X",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Text(
            text = "TRACKER",
            fontSize = 24.sp,
            color = Color(0xFF00E5FF),
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Track battles. Rise to the top.",
            color = Color.LightGray,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    BeybladeTrackerTheme {
        AuthScreenContent(
            state = AuthState(isLoading = true),
            onGoogleClick = {}
        )
    }
}