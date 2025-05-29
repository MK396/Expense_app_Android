package com.example.finanse

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.finanse.data.Expense
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = Repository(app.applicationContext)

    val allExpenses: Flow<List<Expense>> = repo.getAll()

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            repo.insert(listOf(expense))
        }
    }
}
