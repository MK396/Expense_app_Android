package com.example.finanse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.finanse.ui.theme.FinanseTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            FinanseTheme {
                val navController = rememberNavController()
                val viewModel: MainViewModel = viewModel(
                    factory = ViewModelProvider.AndroidViewModelFactory(application))

                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    AppNavigation(navController, viewModel)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: MainViewModel,
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController, viewModel) }
        composable("add_expense") { AddExpenseScreen(viewModel) }
        composable("show_expense") { ExpenseScreen(viewModel) }
        composable("show_income") { IncomeScreen(viewModel) }
        composable("show_chart") { ChartScreen(navController, viewModel) }
    }
}


