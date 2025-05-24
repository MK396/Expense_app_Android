package com.example.finanse

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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun HomeScreen(navController: NavController){
    Surface(modifier = Modifier.fillMaxSize()) {
        Column() {
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
                        text = "1000 zł",
                        style = TextStyle(
                            fontSize = 35.sp,
                        )
                    )
                }
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
                        text = "1000 zł",
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
                            onClick = {navController.navigate("add_expense")},
                            modifier = Modifier
                                .width(120.dp)
                        ) {
                            Text("Dodaj")
                        }
                        Button(
                            onClick = {},
                            modifier = Modifier
                                .width(165.dp)
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
                        text = "1000 zł",
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
                            onClick = {navController.navigate("add_income")},
                            modifier = Modifier
                                .width(120.dp)
                        ) {
                            Text("Dodaj")
                        }
                        Button(
                            onClick = {},
                            modifier = Modifier
                                .width(165.dp)
                        ) {
                            Text("Sprawdź wpłaty")
                        }
                    }
                }
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    val fakeNavController = androidx.navigation.compose.rememberNavController()
    HomeScreen(navController = fakeNavController)
}