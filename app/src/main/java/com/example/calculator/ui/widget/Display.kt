package com.example.calculator.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NormalDisPlay(showResult: String) {
    Box(
        Modifier
            .background(Color.White)
            .border(1.dp, Color.Black)
            .padding(8.dp)
    ) {
        Text(
            showResult,
            Modifier.fillMaxWidth(),
            fontSize = 20.sp,
            maxLines = 1,
            color = Color.Black,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.End
        )
    }
}

@Preview
@Composable
fun testDisPlay() {
    NormalDisPlay("1234")
}