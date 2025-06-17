package com.anjanx44.expensetracker.repository

import com.anjanx44.expensetracker.data.Expense
import com.anjanx44.expensetracker.data.ExpenseDao
import kotlinx.coroutines.flow.Flow

class ExpenseRepository(private val expenseDao: ExpenseDao) {
    suspend fun insertExpense(expense: Expense) = expenseDao.insertExpense(expense)

    fun getAllExpenses(): Flow<List<Expense>> = expenseDao.getAllExpenses()

    fun getExpensesForMonth(month: String, year: String): Flow<List<Expense>> =
        expenseDao.getExpensesForMonth(month, year)
}
