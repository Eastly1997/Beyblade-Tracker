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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.material3.ripple

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
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
import com.lkby.common.theme.BackgroundDark
import com.lkby.common.theme.BeybladeTrackerTheme
import com.lkby.common.theme.CTA
import com.lkby.feature.tournament.domain.model.TournamentFormat
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

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
                    navController.navigate(Route.Home.value) {
                        popUpTo(Route.Splash.value) { inclusive = true }
                    }
                }
                CreateTournamentNavigation.Exit -> {
                    navController.navigate(Route.Home.value) {
                        popUpTo(Route.Splash.value) { inclusive = true }
                    }
                }
            }
        }
    }

    CreateTournamentScreenContent(
        state,
        viewModel::onEvent
    )
}

@Composable
fun CreateTournamentScreenContent(
    state: CreateTournamentState,
    onEvent: (CreateTournamentEvent) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F5F9))
            .padding(16.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus()
            }
    ) {
        CreateTournamentToolbar {  }

        Spacer(Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = state.name,
                    onValueChange = {
                        onEvent(CreateTournamentEvent.NameChanged(it))
                    },
                    label = { Text("Tournament Name *") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF7C3AED),
                        unfocusedBorderColor = Color(0xFFD1D5DB),
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        cursorColor = Color(0xFF7C3AED)
                    ),
                    maxLines = 1
                )

                Column(
                ) {

                    SectionLabel("Schedule")

                    DateTimePickerField(
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
                .background(Color(0xFFF1F5F9))
                .padding(WindowInsets.navigationBars.asPaddingValues())
        ) {
            Button(
                interactionSource =  interaction,
                colors = ButtonDefaults.buttonColors(
                    containerColor = CTA,
                    contentColor = BackgroundDark
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
                            color = Color.White,
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(18.dp)
                        )

                        Spacer(Modifier.width(8.dp))

                        Text("Creating...",
                            color = Color.White
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

        SectionLabel("FORMAT")

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
                            if (isSelected) Color(0xFF7C3AED)
                            else Color.White
                        )
                        .border(
                            1.dp,
                            if (isSelected) Color(0xFF7C3AED)
                            else Color(0xFFE5E7EB),
                            RoundedCornerShape(12.dp)
                        )
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple()
                        ) {
                            onSelect(format)
                        }
                        .padding(horizontal = 12.dp, vertical = 8.dp) // slightly smaller
                ) {
                    Text(
                        text = format.name
                            .lowercase()
                            .replace("_", " ")
                            .replaceFirstChar { it.uppercase() },
                        fontSize = 13.sp,
                        color = if (isSelected) Color.White else Color.Black
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
@Composable
fun FormatDropdown(
    selected: TournamentFormat,
    onSelect: (TournamentFormat) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    Spacer(Modifier.height(8.dp))

    Box(
        Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFE5E7EB))
    )

    Spacer(Modifier.height(16.dp))

    Column {
        Text(
            "FORMAT",
            fontSize = 12.sp,
            letterSpacing = 1.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        OutlinedTextField(
            value = selected.name.replace("_", " "),
            onValueChange = {},
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF7C3AED),
                unfocusedBorderColor = Color(0xFFE5E7EB),

                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,

                cursorColor = Color(0xFF7C3AED)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            readOnly = true,
            trailingIcon = {
                Icon(Icons.Default.ArrowDropDown, null)
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            TournamentFormat.entries.forEach { format ->
                DropdownMenuItem(
                    text = { Text(format.name.replace("_", " ")) },
                    onClick = {
                        onSelect(format)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun TentativeToggle(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple()
            ) {
                onCheckedChange(!checked)
            }
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            checked = checked,
            onCheckedChange = {
                onCheckedChange(it)
            }
        )

        Text(
            "Mark as tentative",
            fontSize = 14.sp
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePickerField(
    dateTime: Long?,
    enabled: Boolean = true,
    onDateTimeSelected: (Long) -> Unit,
    focusManager: FocusManager
) {

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    var tempDate by remember { mutableStateOf<Long?>(null) }

    val formatted = dateTime?.let {
        SimpleDateFormat("MMM dd, hh:mm a", Locale.getDefault())
            .format(Date(it))
    } ?: ""

    OutlinedTextField(
        value = formatted,
        onValueChange = {},
        enabled = false,
        label = { Text("Start Date & Time") },
        modifier = Modifier
            .fillMaxWidth()
            .alpha(if (enabled) 1f else 0.5f)
            .clickable(
                enabled = enabled,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple()
            ) {
                focusManager.clearFocus()
                showDatePicker = true
            },
        readOnly = true,
        trailingIcon = {
            Icon(Icons.Default.DateRange, null)
        },
        colors = OutlinedTextFieldDefaults.colors(
            // Use NORMAL colors even when disabled
            disabledBorderColor = Color(0xFFD1D5DB),
            disabledTextColor = Color.Black,
            disabledLabelColor = Color(0xFF6B7280),
            disabledTrailingIconColor = Color(0xFF6B7280),

            // Keep focused/unfocused consistent
            focusedBorderColor = Color(0xFF7C3AED),
            unfocusedBorderColor = Color(0xFFD1D5DB),

            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White
        )
    )

    // STEP 1: DATE PICKER
    if (showDatePicker) {

        val todayStart = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis

        val selectedDate = dateTime?.toUtcMidnight()
        val safeDate = maxOf(selectedDate ?: todayStart, todayStart)

        val dateState = rememberDatePickerState(
            initialDisplayedMonthMillis = safeDate,
            initialSelectedDateMillis = safeDate,
            selectableDates = object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    return utcTimeMillis >= todayStart
                }
            }
        )

        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    tempDate = dateState.selectedDateMillis
                    showDatePicker = false
                    showTimePicker = true
                }) {
                    Text("Next")
                }
            }
        ) {
            DatePicker(state = dateState)
        }
    }

    // STEP 2: TIME PICKER
    if (showTimePicker && tempDate != null) {

        val calendar = Calendar.getInstance()

        dateTime?.let {
            calendar.timeInMillis = it
        }

        val timeState = rememberTimePickerState(
            initialHour = calendar.get(Calendar.HOUR_OF_DAY),
            initialMinute = calendar.get(Calendar.MINUTE)
        )

        AlertDialog(
            onDismissRequest = { showTimePicker = false },
            confirmButton = {
                TextButton(onClick = {

                    val calendar = Calendar.getInstance()
                    calendar.timeInMillis = tempDate!!

                    calendar.set(Calendar.HOUR_OF_DAY, timeState.hour)
                    calendar.set(Calendar.MINUTE, timeState.minute)

                    onDateTimeSelected(calendar.timeInMillis)

                    showTimePicker = false
                }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showTimePicker = false
                }) {
                    Text("Cancel")
                }
            },
            text = {
                TimePicker(state = timeState)
            }
        )
    }
}

fun Long.toUtcMidnight(): Long {
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
        timeInMillis = this@toUtcMidnight
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
    return calendar.timeInMillis
}

@Composable
fun CreateTournamentToolbar(
    onBack: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "Back"
            )
        }

        Text(
            "Create Tournament",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun SectionLabel(text: String) {
    Text(
        text,
        fontSize = 12.sp,
        letterSpacing = 1.sp,
        color = Color(0xFF6B7280)
    )
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
            onEvent = {}
        )
    }
}