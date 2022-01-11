package com.example.composecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.composecalculator.data.Calculate
import com.example.composecalculator.ui.theme.ComposeCalculatorTheme
import com.example.composecalculator.ui.theme.Dimens

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCalculatorTheme {
                Surface(color = MaterialTheme.colors.background) {
                    CalculatorLayout()
                }
            }
        }
    }
}

@Preview
@Composable
fun CalculatorLayout() {

    var text by remember { mutableStateOf("")}

    Column(verticalArrangement = Arrangement.spacedBy(Dimens.margin16)) {
        TextField(
            value = text,
            onValueChange = {},
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
            readOnly = true,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimens.margin16),
            modifier = Modifier.padding(start = Dimens.margin16,end = Dimens.margin16)
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    text = ""
                },
                modifier = Modifier.weight(1F)
            ) {
                Text("AC")
            }

            Button(
                onClick = {
                    text += "("
                },
                modifier = Modifier.weight(1F)
            ) {
                Text("(")
            }

            Button(
                onClick = {
                    text += ")"
                },
                modifier = Modifier.weight(1F)
            ) {
                Text(")")
            }

            Button(
                onClick = {
                    text += "%"
                },
                modifier = Modifier.weight(1F)
            ) {
                Text("%")
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimens.margin16),
            modifier = Modifier.padding(start = Dimens.margin16,end = Dimens.margin16)
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    text += "7"
                },
                modifier = Modifier.weight(1F)
            ) {
                Text("7")
            }

            Button(
                onClick = {
                    text += "8"
                },
                modifier = Modifier.weight(1F)
            ) {
                Text("8")
            }

            Button(
                onClick = {
                    text += "9"
                },
                modifier = Modifier.weight(1F)
            ) {
                Text("9")
            }

            Button(
                onClick = {
                    text += "÷"
                },
                modifier = Modifier.weight(1F)
            ) {
                Text("÷")
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimens.margin16),
            modifier = Modifier.padding(start = Dimens.margin16,end = Dimens.margin16)
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    text += "4"
                },
                modifier = Modifier.weight(1F)
            ) {
                Text("4")
            }

            Button(
                onClick = {
                    text += "5"
                },
                modifier = Modifier.weight(1F)
            ) {
                Text("5")
            }

            Button(
                onClick = {
                    text += "6"
                },
                modifier = Modifier.weight(1F)
            ) {
                Text("6")
            }

            Button(
                onClick = {
                    text += "×"
                },
                modifier = Modifier.weight(1F)
            ) {
                Text("×")
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimens.margin16),
            modifier = Modifier.padding(start = Dimens.margin16,end = Dimens.margin16)
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    text += "1"
                },
                modifier = Modifier.weight(1F)
            ) {
                Text("1")
            }

            Button(
                onClick = {
                    text += "2"
                },
                modifier = Modifier.weight(1F)
            ) {
                Text("2")
            }

            Button(
                onClick = {
                    text += "3"
                },
                modifier = Modifier.weight(1F)
            ) {
                Text("3")
            }

            Button(
                onClick = {
                    text += "-"
                },
                modifier = Modifier.weight(1F)
            ) {
                Text("-")
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimens.margin16),
            modifier = Modifier.padding(start = Dimens.margin16, end = Dimens.margin16, bottom = Dimens.margin16)
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    text += "0"
                },
                modifier = Modifier.weight(1F)
            ) {
                Text("0")
            }

            Button(
                onClick = {
                    text += "."
                },
                modifier = Modifier.weight(1F)
            ) {
                Text(".")
            }

            Button(
                onClick = {
                    text = Calculate().getResultString(text)
                },
                modifier = Modifier.weight(1F)
            ) {
                Text("=")
            }

            Button(
                onClick = {
                    text += "+"
                },
                modifier = Modifier.weight(1F)
            ) {
                Text("+")
            }
        }
    }

}
