package com.example.finanse.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expenses: List<Expense>)

    @Delete
    suspend fun delete(expenses: List<Expense>)

    @Query("SELECT * FROM expense_table")
    fun getAll(): Flow<List<Expense>>

    @Query("SELECT * FROM expense_table WHERE type = 'Wydatek'")
    fun getExpense(): Flow<List<Expense>>

    @Query("SELECT * FROM expense_table WHERE type = 'Przychód'")
    fun getIncome(): Flow<List<Expense>>


}