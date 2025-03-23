package com.example.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Layout(calculator: Calculator) {
    var input by remember { mutableStateOf("") } //Guarda o valor dos botoões pressionados
    var result by remember { mutableStateOf("0") } //Guarda o resultado final
    var lastOperation by remember { mutableStateOf<String?>(null) } //Variável que guarda a operação a ser realizada

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = if (input.isEmpty()) result.toString() else "$input",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            color = Color.Black,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.End
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { input += "1" }, modifier = Modifier.weight(1f)) {
                Text(text = "1")
            }
            Button(onClick = { input += "2" }, modifier = Modifier.weight(1f)) {
                Text(text = "2")
            }
            Button(onClick = { input += "3" }, modifier = Modifier.weight(1f)) {
                Text(text = "3")
            }
            Button(onClick = {
                if (input.isNotEmpty()) {
                    calculator.num1 = input.toInt() // seta o valor do input para num1
                    input = "" // Limpa o valor do input
                    lastOperation = "+" // Guarda a operação de soma
                }
            }, modifier = Modifier.weight(1f)) {
                Text(text = "+")
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { input += "4" }, modifier = Modifier.weight(1f)) {
                Text(text = "4")
            }
            Button(onClick = { input += "5" }, modifier = Modifier.weight(1f)) {
                Text(text = "5")
            }
            Button(onClick = { input += "6" }, modifier = Modifier.weight(1f)) {
                Text(text = "6")
            }
            Button(onClick = {
                if (input.isNotEmpty()) {
                    calculator.num1 = input.toInt() // seta o valor do input para num1
                    input = "" // Limpa o valor do input
                    lastOperation = "-" // Guarda a operação de subtração
                }
            }, modifier = Modifier.weight(1f)) {
                Text(text = "-")
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { input += "7" }, modifier = Modifier.weight(1f)) {
                Text(text = "7")
            }
            Button(onClick = { input += "8" }, modifier = Modifier.weight(1f)) {
                Text(text = "8")
            }
            Button(onClick = { input += "9" }, modifier = Modifier.weight(1f)) {
                Text(text = "9")
            }
            Button(onClick = {
                if (input.isNotEmpty()) {
                    calculator.num1 = input.toInt() // seta o valor do input para num1
                    input = "" // Limpa o valor do input
                    lastOperation = "*" // Guarda a operação de multiplicação
                }
            }, modifier = Modifier.weight(1f)) {
                Text(text = "*")
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                input = "" // Limpa o valor do input
                result = "0" // Reseta o resultado
                lastOperation = null}, // Reseta a operação
                modifier = Modifier.weight(1f)) {
                Text(text = "C")
            }
            Button(onClick = { input += "0" }, modifier = Modifier.weight(1f)) {
                Text(text = "0")
            }
            Button(onClick = {
                if (input.isNotEmpty()) {
                    calculator.num2 = input.toInt() // seta o valor do input para num2
                    input = "" // Limpa o valor do input

                    // Executa a operação baseada na última operação selecionada
                    result = when (lastOperation) {
                        "+" -> calculator.sum().toString()
                        "-" -> calculator.subtract().toString()
                        "*" -> calculator.multiply().toString()
                        "/" -> calculator.divide()
                        else -> calculator.num2.toString()
                    } // Switch case para saber qual operação da classe Calculator realizar com base no operador guardado

                    lastOperation = null // Reseta a operação após o cálculo
                }
            }, modifier = Modifier.weight(1f)) {
                Text(text = "=")
            }
            Button(onClick = {
                if (input.isNotEmpty()) {
                    calculator.num1 = input.toInt() // seta o valor do input para num1
                    input = "" // Limpa o valor do input
                    lastOperation = "/" // Guarda a operação de divisão
                }
            }, modifier = Modifier.weight(1f)) {
                Text(text = "/")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShowLayout() {
    Layout(Calculator())
} // Função para mostrar o layout do app