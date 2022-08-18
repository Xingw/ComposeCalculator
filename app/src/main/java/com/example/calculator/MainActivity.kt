package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.calculator.arithmetic.Calculator
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.widget.MyCalculator

class MainActivity : ComponentActivity() {
    var displayText by mutableStateOf("")
    val calculator = Calculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                MyCalculator(::onKeyClick, displayText)
            }
        }
    }

    private fun onKeyClick(key: String) {
        displayText = key
    }
}

