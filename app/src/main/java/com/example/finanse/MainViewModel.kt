package com.example.finanse

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.finanse.data.Expense
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = Repository(app.applicationContext)

    val allExpenses: Flow<List<Expense>> = repo.getAll()

    val balance: Flow<Double> = allExpenses.map { expenses ->
        expenses.sumOf { if (it.type == "Przychód") it.amount else -it.amount }
    }

    val totalExpenses: Flow<Double> = allExpenses.map { expenses ->
        expenses.filter { it.type == "Wydatek" }.sumOf { it.amount }
    }

    val totalIncome: Flow<Double> = allExpenses.map { expenses ->
        expenses.filter { it.type == "Przychód" }.sumOf { it.amount }
    }



    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            repo.insert(listOf(expense))
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            repo.delete(listOf(expense))
        }
    }

    fun clearAllExpenses() {
        viewModelScope.launch {
            repo.deleteAll()
        }
    }

}
