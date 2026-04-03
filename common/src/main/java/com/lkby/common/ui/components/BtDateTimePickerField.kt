package com.lkby.common.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusManager
import com.lkby.common.extensions.getLocalTodayUtcMidnight
import com.lkby.common.extensions.toUtcMidnight
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BtDateTimePickerField(
    dateTime: Long?,
    enabled: Boolean = true,
    onDateTimeSelected: (Long) -> Unit,
    focusManager: FocusManager,
    label: String = "Start Date & Time"
) {
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }
    var tempDate by remember { mutableStateOf<Long?>(null) }

    val formatted = dateTime?.let {
        SimpleDateFormat("MMM dd, hh:mm a", Locale.getDefault())
            .format(Date(it))
    } ?: ""

    BtTextField(
        value = formatted,
        onValueChange = {},
        enabled = false,
        label = label,
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
        }
    )

    // STEP 1: DATE PICKER
    if (showDatePicker) {
        val todayStartUtc = getLocalTodayUtcMidnight()
        
        // Map the current selection to UTC midnight for the DatePicker
        val initialSelectedDate = dateTime?.toUtcMidnight() ?: todayStartUtc

        val dateState = rememberDatePickerState(
            initialSelectedDateMillis = initialSelectedDate,
            initialDisplayedMonthMillis = initialSelectedDate,
            selectableDates = object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    return utcTimeMillis >= todayStartUtc
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
                    // Extract Y/M/D from UTC midnight
                    val utcCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
                        timeInMillis = tempDate!!
                    }
                    
                    // Apply to Local Calendar
                    val finalLocalCalendar = Calendar.getInstance().apply {
                        set(Calendar.YEAR, utcCalendar.get(Calendar.YEAR))
                        set(Calendar.MONTH, utcCalendar.get(Calendar.MONTH))
                        set(Calendar.DAY_OF_MONTH, utcCalendar.get(Calendar.DAY_OF_MONTH))
                        set(Calendar.HOUR_OF_DAY, timeState.hour)
                        set(Calendar.MINUTE, timeState.minute)
                        set(Calendar.SECOND, 0)
                        set(Calendar.MILLISECOND, 0)
                    }
                    
                    onDateTimeSelected(finalLocalCalendar.timeInMillis)
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