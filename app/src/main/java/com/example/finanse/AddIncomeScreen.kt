package com.example.finanse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddIncomeScreen(){
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
                    text = "Dodaj przychód",
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
                    .padding(top = 40.dp)
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Nazwa", fontSize = 20.sp)
                    OutlinedTextField(
                        value = "", onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .background(color = Color.White))
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(text = "Kwota", fontSize = 20.sp)
                    OutlinedTextField(
                        value = "", onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .background(color = Color.White))
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(text = "Data", fontSize = 20.sp)
                    OutlinedTextField(
                        value = "", onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .background(color = Color.White))
                    Spacer(modifier = Modifier.height(30.dp))
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                    ) { Text(text = "Dodaj", fontSize = 15.sp) }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddIncomePreview(){
    AddIncomeScreen()
}