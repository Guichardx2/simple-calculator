package com.example.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Calculator {
    var num1 by mutableStateOf(0)
    var num2 by mutableStateOf(0)

    fun sum(): Int {
        return num1 + num2
    }

    fun subtract(): Int {
        return num1 - num2
    }

    fun multiply(): Int {
        return num1 * num2
    }

    fun divide(): String {
        return if (num2 != 0) {
            (num1.toDouble() / num2.toDouble()).toString()
        } else {
            "Impossível dividir por 0"
        }
    }
}