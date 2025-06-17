package com.anjanx44.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.anjanx44.expensetracker.data.ExpenseDatabase
import com.anjanx44.expensetracker.repository.ExpenseRepository
import com.anjanx44.expensetracker.ui.screens.AddExpenseScreen
import com.anjanx44.expensetracker.ui.screens.ExpenseListScreen
import com.anjanx44.expensetracker.ui.theme.ExpenseTrackerTheme
import com.anjanx44.expensetracker.viewmodel.ExpenseViewModel
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpenseTrackerTheme {
                val db = Room.databaseBuilder(
                    applicationContext,
                    ExpenseDatabase::class.java,
                    "expense_db"
                ).build()
                val repository = ExpenseRepository(db.expenseDao())
                val viewModel: ExpenseViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            @Suppress("UNCHECKED_CAST")
                            return ExpenseViewModel(repository) as T
                        }
                    }
                )
                val expenses by viewModel.expenses.collectAsState()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        AddExpenseScreen(onAddExpense = { viewModel.addExpense(it) })
                        Spacer(modifier = Modifier.height(16.dp))
                        ExpenseListScreen(expenses = expenses, monthlyTotal = viewModel.getMonthlyTotal())
                    }
                }
            }
        }
    }
}