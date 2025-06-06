package com.example.finanse

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finanse.data.Expense
import java.time.LocalDate
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Popup
import java.text.SimpleDateFormat
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerFieldToModal(
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // Przechowuj wybraną datę jako millis (Long)
    var selectedDateMillis by remember {
        mutableStateOf(selectedDate.toMillisOrNull())
    }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = selectedDateMillis)

    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedDateMillis?.let { it.convertMillisToDate() } ?: "",
            onValueChange = { },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Wybierz datę"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .clickable { showDatePicker = true }
        )

        if (showDatePicker) {
            Popup(
                onDismissRequest = { showDatePicker = false },
                alignment = Alignment.TopStart
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Column {
                        DatePicker(
                            state = datePickerState,
                            showModeToggle = false
                        )
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            TextButton(onClick = { showDatePicker = false }) {
                                Text("Anuluj")
                            }
                            TextButton(onClick = {
                                val millis = datePickerState.selectedDateMillis
                                if (millis != null) {
                                    selectedDateMillis = millis
                                    onDateSelected(millis.convertMillisToDate(format = "dd.MM.yyyy"))
                                }
                                showDatePicker = false
                            }) {
                                Text("OK")
                            }
                        }
                    }
                }
            }
        }
    }
}

// Pomocnicza funkcja: String 'dd.MM.yyyy' → millis
fun String.toMillisOrNull(): Long? = try {
    val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    sdf.parse(this)?.time
} catch (_: Exception) { null }

// Pomocnicza funkcja: millis → String 'dd.MM.yyyy'
fun Long.convertMillisToDate(format: String = "dd.MM.yyyy"): String {
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    return formatter.format(Date(this))
}



@Composable
fun TypeMenu(typeValue: String, onTypeChange: (String) -> Unit, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = typeValue.ifEmpty { "Wybierz typ" },
            onValueChange = { /* readonly */ },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Rozwiń menu"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .clickable { expanded = true }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
                .background(color = Color.White)
        ) {
            DropdownMenuItem(
                text = { Text("Przychód") },
                onClick = {
                    onTypeChange("Przychód")
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Wydatek") },
                onClick = {
                    onTypeChange("Wydatek")
                    expanded = false
                }
            )
        }
    }
}


@Composable
fun AddExpenseScreen(viewModel: MainViewModel){
    var typeValue by remember { mutableStateOf("") }
    var nameValue by remember { mutableStateOf("") }
    var amountValue by remember { mutableStateOf("") }
    var dateValue by remember { mutableStateOf("") }

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
                    text = "Dodaj wydatek/przychód",
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
                    Text(text = "Typ", fontSize = 20.sp)
                    TypeMenu(
                        typeValue = typeValue,
                        onTypeChange = { typeValue = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .background(color = Color.White))
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(text = "Nazwa", fontSize = 20.sp)
                    OutlinedTextField(
                        value = nameValue,
                        onValueChange = {nameValue = it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .background(color = Color.White))
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(text = "Kwota", fontSize = 20.sp)
                    OutlinedTextField(
                        value = amountValue,
                        onValueChange = {amountValue = it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .background(color = Color.White))
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(text = "Data", fontSize = 20.sp)
                    DatePickerFieldToModal(
                        selectedDate = dateValue,
                        onDateSelected = { dateValue = it },
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .background(color = Color.White)
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    val context = LocalContext.current
                    Button(
                        onClick = {
                            val expense = Expense(
                                type = typeValue,
                                name = nameValue,
                                amount = amountValue.toDoubleOrNull() ?: 0.0,
                                date = dateValue
                            )
                            if (typeValue.isEmpty() || nameValue.isEmpty() || amountValue.isEmpty() || dateValue.isEmpty())
                            {
                                Toast.makeText(context, "Wypełnij wszystkie pola", Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            val amount = amountValue.toDoubleOrNull()
                            if (amount == null || amount <= 0)
                            {
                                Toast.makeText(context, "Kwota musi być większa od 0", Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            else if (typeValue == "Wydatek") {
                                viewModel.addExpense(expense)
                                Toast.makeText(context, "Dodano wydatek", Toast.LENGTH_SHORT).show()
                                typeValue = ""
                                nameValue = ""
                                amountValue = ""
                                dateValue = ""
                            }
                            else
                            {
                                viewModel.addExpense(expense)
                                Toast.makeText(context, "Dodano przychód", Toast.LENGTH_SHORT).show()
                                typeValue = ""
                                nameValue = ""
                                amountValue = ""
                                dateValue = ""
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) { Text(text = "Dodaj", fontSize = 15.sp) }
                }
            }
        }
    }
}