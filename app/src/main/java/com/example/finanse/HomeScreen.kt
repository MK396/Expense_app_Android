package com.example.finanse

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun HomeScreen(navController: NavController, viewModel: MainViewModel){

    val balance by viewModel.balance.collectAsState(initial = 0.0)
    val totalExpenses by viewModel.totalExpenses.collectAsState(initial = 0.0)
    val totalIncome by viewModel.totalIncome.collectAsState(initial = 0.0)
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Główna") },
                    label = { Text("Główna") },
                    selected = true,
                    onClick = {  }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Build, contentDescription = "Wykres") },
                    label = { Text("Wykres") },
                    selected = false,
                    //onClick = { Toast.makeText(context, "Ta funkcja jeszcze nie działa", Toast.LENGTH_SHORT).show() },
                    onClick = { navController.navigate("show_chart") }

                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxSize()
                    .background(color = Color(0xFF16A0F0)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Aplikacja  wydatków",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                    textAlign = TextAlign.Center,
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Stan konta",
                        style = TextStyle(
                            fontSize = 20.sp,
                        ),
                        textAlign = TextAlign.Center,
                    )
                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )
                    Text(
                        text = "%.2f zł".format(balance),
                        style = TextStyle(
                            fontSize = 35.sp,
                        )
                    )
                }
            }
            Button(
                onClick = {navController.navigate("add_expense")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
            ) {
                Text("Dodaj")
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Wydatki",
                        style = TextStyle(
                            fontSize = 20.sp,
                        ),
                        textAlign = TextAlign.Center,
                    )
                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )
                    Text(
                        text = "%.2f zł".format(totalExpenses),
                        style = TextStyle(
                            fontSize = 35.sp,
                        )
                    )
                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {navController.navigate("show_expense")},
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text("Sprawdź wydatki")
                        }
                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Wpłaty",
                        style = TextStyle(
                            fontSize = 20.sp,
                        ),
                        textAlign = TextAlign.Center,
                    )
                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )
                    Text(
                        text = "%.2f zł".format(totalIncome),
                        style = TextStyle(
                            fontSize = 35.sp,
                        )
                    )
                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {navController.navigate("show_income")},
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text("Sprawdź wpłaty")
                        }
                    }
                }
            }
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            var showDialog by remember { mutableStateOf(false) }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp),
                onClick = { showDialog = true }
            ) {
                Text("Reset bazy danych")
            }
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    text = { Text("Czy zresetować bazę danych?") },
                    confirmButton = {
                        TextButton(onClick = {
                            viewModel.clearAllExpenses()
                            showDialog = false
                        }) {
                            Text("Tak")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("Anuluj")
                        }
                    }
                )
            }
        }
    }
}