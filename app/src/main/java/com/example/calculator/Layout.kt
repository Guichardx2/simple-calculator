package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
    var result by remember { mutableStateOf<Any>(0) } //Guarda o resultado final
    var lastOperation by remember { mutableStateOf<String?>(null) } //Variável que guarda a operação a ser realizada

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = when {
                    lastOperation != null && input.isEmpty() -> "${calculator.num1} $lastOperation"
                    input.isNotEmpty() && lastOperation != null -> "${calculator.num1} $lastOperation $input"
                    input.isNotEmpty() -> input
                    else -> result.toString()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                color = Color.Black,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.End
            )
        }

        // Numpad na parte inferior
        val numPad = listOf(
            listOf("1", "2", "3", "+"),
            listOf("4", "5", "6", "-"),
            listOf("7", "8", "9", "*"),
            listOf("C", "0", "=", "/")
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
        ) {
            numPad.forEach { row ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    row.forEach { buttonText ->
                        LayoutButton(text = buttonText, onClick = {
                            when (buttonText) {
                                "C" -> {
                                    input = ""
                                    result = 0
                                    lastOperation = null
                                }
                                "=" -> {
                                    if (input.isNotEmpty() && lastOperation != null) {
                                        calculator.num2 = input.toInt()
                                        input = ""
                                        result = when (lastOperation) {
                                            "+" -> calculator.sum()
                                            "-" -> calculator.subtract()
                                            "*" -> calculator.multiply()
                                            "/" -> String.format("%.4f", calculator.divide()).toDouble()
                                            else -> calculator.num2
                                        }
                                        lastOperation = null
                                    }
                                }
                                "+", "-", "*", "/" -> {
                                    if (input.isNotEmpty()) {
                                        calculator.num1 = input.toInt()
                                        lastOperation = buttonText
                                        input = ""
                                    }
                                }
                                else -> {
                                    input += buttonText
                                }
                            }
                        }, modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

//fun mathExpressionSolver(expression: String): Double {
//    val expressionList = expression.split(" ")
//    val num1 = expressionList[0].toDouble()
//    val operator = expressionList[1]
//    val num2 = expressionList[2].toDouble()
//    return when (operator) {
//        "+" -> num1 + num2
//        "-" -> num1 - num2
//        "*" -> num1 * num2
//        "/" -> num1 / num2
//        else -> 0.0
//    }
//}

@Composable
fun LayoutButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(4.dp).size(64.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun ShowLayout() {
    Layout(Calculator())
} // Função para mostrar o layout do app