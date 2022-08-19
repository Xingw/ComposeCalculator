package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.core.text.isDigitsOnly
import com.example.calculator.arithmetic.Calculator
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.widget.MyCalculator

class MainActivity : ComponentActivity() {
    private var displayText by mutableStateOf("0")
    val calculator = Calculator()
    var isFinish = false
    var isError = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                MyCalculator(::onKeyClick, displayText)
            }
        }
    }

    private fun onKeyClick(key: String) {
        when (key) {
            "=" -> {
                updateDisplay(calculator.calculate(displayText))
                isFinish = true
                if (displayText == "ERROR") {
                    isError = true
                }
            }
            "C" -> {
                if (isError) {
                    onKeyClick("AC")
                }
                updateDisplay(displayText.dropLast(1))
            }
            "AC" -> {
                updateDisplay("")
                clearFlag()
            }
            else -> {
                if (isError || (isFinish && key.isDigitsOnly())) {
                    onKeyClick("AC")
                }
                if (displayText == "0") {
                    displayText = ""
                }
                updateDisplay(displayText + key)
            }
        }
    }

    private fun clearFlag() {
        isFinish = false
        isError = false
    }

    private fun updateDisplay(text: String) {
        displayText = text.ifEmpty {
            "0"
        }
    }
}

