package com.anjanx44.expensetracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anjanx44.expensetracker.data.Expense
import com.anjanx44.expensetracker.repository.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Calendar

class ExpenseViewModel(private val repository: ExpenseRepository) : ViewModel() {
    private val _expenses = MutableStateFlow<List<Expense>>(emptyList())
    val expenses: StateFlow<List<Expense>> = _expenses.asStateFlow()

    init {
        loadCurrentMonthExpenses()
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            repository.insertExpense(expense)
            loadCurrentMonthExpenses()
        }
    }

    fun loadCurrentMonthExpenses() {
        val calendar = Calendar.getInstance()
        val month = String.format("%02d", calendar.get(Calendar.MONTH) + 1)
        val year = calendar.get(Calendar.YEAR).toString()
        viewModelScope.launch {
            repository.getExpensesForMonth(month, year).collectLatest {
                _expenses.value = it
            }
        }
    }

    fun getMonthlyTotal(): Double {
        return expenses.value.sumOf { it.amount }
    }
}
