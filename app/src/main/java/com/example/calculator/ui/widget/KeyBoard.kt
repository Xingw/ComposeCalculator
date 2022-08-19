package com.example.calculator.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.CalculatorTheme

@Composable
fun KeyBoard(onClick: (String) -> Unit = {}) {
    Box(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        GridView(rowNum = 5, columnNum = 4, itemList = defaultKeyMapList, onClick)
    }
}

val defaultKeyMapList: MutableList<String> = mutableListOf(
    "(",
    ")",
    "C",
    "AC",
    "7",
    "8",
    "9",
    "/",
    "4",
    "5",
    "6",
    "*",
    "1",
    "2",
    "3",
    "-",
    "0",
    ".",
    "=",
    "+"
)

@Composable
fun Key(text: String, modifier: Modifier = Modifier, onClick: (String) -> Unit = {}) {
    Button(
        onClick = {
            onClick(text)
        },
        modifier,
        shape = RoundedCornerShape(4.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        Text(text = text, color = Color.White, fontSize = 12.sp)
    }
}

@Composable
fun GridView(rowNum: Int, columnNum: Int, itemList: List<String>, onClick: (String) -> Unit = {}) {
    Column(Modifier.padding(top = 5.dp)) {
        for (i in 0 until rowNum) {
            Row {
                for (k in 0 until columnNum) {
                    Key(text = itemList[i * columnNum + k], Modifier.weight(1f), onClick)
                    if (k != columnNum - 1) {
                        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                    }
                }
            }
            if (i != rowNum - 1) {
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            }
        }
    }
}


@Preview
@Composable
fun Preview() {
    CalculatorTheme {
        KeyBoard()
    }
}
