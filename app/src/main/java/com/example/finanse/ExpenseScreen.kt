package com.example.finanse

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.finanse.data.Expense

@Composable
fun ExpenseScreen(viewModel: MainViewModel) {
    // Pobieramy dane jako State z Flow
    val expenses by viewModel.allExpenses.collectAsState(initial = emptyList())

    Column {
        Button(onClick = {
            val expense = Expense(type = "Wydatek", name = "Testowy", amount = 123.0, date = "2025-05-29")
            viewModel.addExpense(expense)
        }) {
            Text("Dodaj testowy wydatek")
        }
        LazyColumn {
            items(expenses) { expense ->
                Text("${expense.name} - ${expense.amount} zł")
            }
        }
    }
}