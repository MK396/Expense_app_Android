package com.example.finanse

import android.content.Context
import com.example.finanse.data.Expense
import com.example.finanse.data.ExpenseDao
import com.example.finanse.data.ExpenseDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class Repository(context: Context): ExpenseDao {
    private val dao = ExpenseDb.getInstance(context).expenseDao()

    override suspend fun insert(expenses: List<Expense>) = withContext(Dispatchers.IO) {
        dao.insert(expenses)

    }

    override suspend fun delete(expenses: List<Expense>) = withContext(Dispatchers.IO) {
        dao.delete(expenses)
    }

    override fun getAll(): Flow<List<Expense>> {
        return dao.getAll()
    }

    override fun getExpense(): Flow<List<Expense>> {
        return dao.getExpense()
    }

    override fun getIncome(): Flow<List<Expense>> {
        return dao.getIncome()
    }
}