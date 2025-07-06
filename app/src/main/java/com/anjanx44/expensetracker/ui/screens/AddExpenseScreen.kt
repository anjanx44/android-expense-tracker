package com.anjanx44.expensetracker.ui.screens

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.anjanx44.expensetracker.data.Expense
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AddExpenseScreen(onAddExpense: (Expense) -> Unit) {
    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }
    var selectedDateMillis by remember { mutableStateOf<Long?>(null) }
    val dateFormat = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }
    val dateDisplay = selectedDateMillis?.let { dateFormat.format(Date(it)) } ?: "Select Date (optional)"

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Category") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
                .defaultMinSize(minHeight = 56.dp) // Ensures the Box is at least as tall as the TextField
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    android.widget.Toast.makeText(context, "Date field clicked", android.widget.Toast.LENGTH_SHORT).show()
                    val now = Calendar.getInstance()
                    DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            val pickedCal = Calendar.getInstance()
                            pickedCal.set(year, month, dayOfMonth, 0, 0, 0)
                            pickedCal.set(Calendar.MILLISECOND, 0)
                            selectedDateMillis = pickedCal.timeInMillis
                        },
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }
        ) {
            OutlinedTextField(
                value = dateDisplay,
                onValueChange = {},
                label = { Text("Date") },
                modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit) {}, // disables pointer events for the TextField itself
                readOnly = true,
                enabled = false // disables internal click handling
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val amt = amount.toDoubleOrNull() ?: 0.0
                val dateMillis = selectedDateMillis ?: Calendar.getInstance().timeInMillis
                if (amt > 0 && description.isNotBlank() && category.isNotBlank()) {
                    onAddExpense(
                        Expense(
                            amount = amt,
                            description = description,
                            date = dateMillis,
                            category = category
                        )
                    )
                    amount = ""
                    description = ""
                    category = ""
                    selectedDateMillis = null
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Expense")
        }
    }
}
