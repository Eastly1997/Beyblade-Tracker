package com.lkby.feature.tournament.presentation.create

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lkby.common.navigation.Route
import com.lkby.common.ui.components.BtDateTimePickerField
import com.lkby.common.ui.components.BtSectionLabel
import com.lkby.common.ui.components.BtTextField
import com.lkby.common.ui.components.BtToolbar
import com.lkby.common.ui.theme.BeybladeTrackerTheme
import com.lkby.feature.tournament.domain.model.TournamentFormat
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CreateTournamentScreen(
    navController: NavController,
    userId: String,
    viewModel: CreateTournamentViewModel = koinViewModel(parameters = { parametersOf(userId) })
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.navigation.collect { nav ->
            when (nav) {
                is CreateTournamentNavigation.Success -> {
                    navController.navigate(Route.Home) {
                        popUpTo(Route.Splash) { inclusive = true }
                    }
                }
                CreateTournamentNavigation.Exit -> {
                    navController.navigate(Route.Home) {
                        popUpTo(Route.Splash) { inclusive = true }
                    }
                }
            }
        }
    }

    CreateTournamentScreenContent(
        state = state,
        onEvent = viewModel::onEvent,
        onBack = { navController.popBackStack() }
    )
}

@Composable
fun CreateTournamentScreenContent(
    state: CreateTournamentState,
    onEvent: (CreateTournamentEvent) -> Unit,
    onBack: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus()
            }
    ) {
        BtToolbar(
            title = "Create Tournament",
            onBack = onBack
        )

        Spacer(Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                BtTextField(
                    value = state.name,
                    onValueChange = {
                        onEvent(CreateTournamentEvent.NameChanged(it))
                    },
                    label = "Tournament Name *"
                )

                Column {
                    BtSectionLabel("Schedule")

                    BtDateTimePickerField(
                        dateTime = state.dateTime,
                        enabled = !state.isTentative,
                        onDateTimeSelected = {
                            onEvent(CreateTournamentEvent.DateTimeSelected(it))
                        },
                        focusManager = focusManager
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 3.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = ripple()
                            ) {
                                focusManager.clearFocus()
                                onEvent(CreateTournamentEvent.TentativeChanged(!state.isTentative))
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            modifier = Modifier
                                .padding(
                                    start = 12.dp,
                                    top = 7.dp,
                                    bottom = 7.dp
                                )
                                .size(24.dp),
                            checked = state.isTentative,
                            onCheckedChange = {
                                focusManager.clearFocus()
                                onEvent(CreateTournamentEvent.TentativeChanged(it))
                            }
                        )

                        Text(
                            modifier = Modifier.padding(
                                start = 8.dp,
                                top = 7.dp,
                                bottom = 7.dp
                            ),
                            text = "Mark as tentative",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                // FORMAT DROPDOWN
                FormatSelector(
                    selected = state.format,
                    onSelect = {
                        focusManager.clearFocus()
                        onEvent(CreateTournamentEvent.FormatChanged(it))
                    }
                )
            }
        }

        Spacer(Modifier.weight(1f))

        val interaction = remember { MutableInteractionSource() }
        val isPressed by interaction.collectIsPressedAsState()

        val scale by animateFloatAsState(
            targetValue = if (isPressed) 0.97f else 1f,
            label = ""
        )
        Box(
            modifier = Modifier.fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(WindowInsets.navigationBars.asPaddingValues())
        ) {
            Button(
                interactionSource =  interaction,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = RoundedCornerShape(16.dp),
                onClick = { onEvent(CreateTournamentEvent.Create) },
                enabled = !state.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Brush.horizontalGradient(
                        listOf(
                            Color(0xFFFACC15),
                            Color(0xFFFBBF24)
                        )
                    ))
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    }
            ) {

                if (state.isLoading) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(18.dp)
                        )

                        Spacer(Modifier.width(8.dp))

                        Text("Creating...",
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                } else {
                    Text(
                        "Create Tournament",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
fun FormatSelector(
    selected: TournamentFormat,
    onSelect: (TournamentFormat) -> Unit
) {

    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {

        BtSectionLabel("FORMAT")

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TournamentFormat.entries.forEach { format ->

                val isSelected = format == selected

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (isSelected) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.surface
                        )
                        .border(
                            1.dp,
                            if (isSelected) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.outlineVariant,
                            RoundedCornerShape(12.dp)
                        )
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple()
                        ) {
                            onSelect(format)
                        }
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = format.name
                            .lowercase()
                            .replace("_", " ")
                            .replaceFirstChar { it.uppercase() },
                        fontSize = 13.sp,
                        color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        // Dynamic helper text
        Text(
            text = when (selected) {
                TournamentFormat.SINGLE_ELIMINATION -> "Fast and simple format"
                TournamentFormat.DOUBLE_ELIMINATION -> "Players get a second chance"
                TournamentFormat.ROUND_ROBIN -> "Everyone plays everyone"
            },
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CreateTournamentLoading() {
    BeybladeTrackerTheme {
        CreateTournamentScreenContent(
            state = CreateTournamentState(
                isTentative = false,
                isLoading = false
            ),
            onEvent = {},
            onBack = {}
        )
    }
}