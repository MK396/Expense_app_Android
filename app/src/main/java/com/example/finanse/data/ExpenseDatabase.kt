package com.example.finanse.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Expense::class], version = 1)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
}

object ExpenseDb{
    private var db: ExpenseDatabase? = null

    fun getInstance(context: Context): ExpenseDatabase{
        if(db == null){
            db = Room.databaseBuilder(
                context,
                ExpenseDatabase::class.java,
                "expense_database").build()
        }
        return db!!
    }
}