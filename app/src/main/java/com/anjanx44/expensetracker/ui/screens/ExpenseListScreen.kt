package com.anjanx44.expensetracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anjanx44.expensetracker.data.Expense
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ExpenseListScreen(expenses: List<Expense>, monthlyTotal: Double) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Monthly Total: \u09F3${"%.2f".format(monthlyTotal)}",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(expenses) { expense ->
                ExpenseItem(expense)
                Divider()
            }
        }
    }
}

@Composable
fun ExpenseItem(expense: Expense) {
    val dateFormat = remember { SimpleDateFormat("dd MMM yyyy", Locale.getDefault()) }
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = "${expense.description} - \u09F3${expense.amount}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Category: ${expense.category}", style = MaterialTheme.typography.bodyMedium)
        Text(text = dateFormat.format(Date(expense.date)), style = MaterialTheme.typography.bodySmall)
    }
}
