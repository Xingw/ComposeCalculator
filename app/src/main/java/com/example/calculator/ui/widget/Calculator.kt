package com.example.calculator.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun MyCalculator(onClick: (String) -> Unit = {}, displayText:String = "123") {
    Column(
        Modifier
            .padding(top = 40.dp)) {
        NormalDisPlay(displayText)
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        KeyBoard(onClick)
    }
}