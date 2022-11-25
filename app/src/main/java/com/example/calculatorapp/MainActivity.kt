@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.calculatorapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculatorapp.components.InputFiled
import com.example.calculatorapp.ui.theme.CalculatorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
//                TopHeader()
                MainContent()
            }
        }
    }
}

@Preview
@Composable
fun TopHeader() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(shape = CircleShape.copy(all = CornerSize(12.dp))), color = Color(0xFF882222)
    ) {

        Column(
            modifier = Modifier.padding(all = 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Text(text = "Total Persons", style = MaterialTheme.typography.h4)
            Text(
                text = "$ 1784",
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold)
            )
        }

    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    CalculatorAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

@Preview
@Composable
fun MainContent() {
    BillForm(billAmt = {
            it -> println("Value is change $it")
    })

}

@Composable
fun BillForm(modifier: Modifier = Modifier, billAmt: (String) -> Unit) {
    val textFieldValue = remember { mutableStateOf("") }

    val isValid = remember(textFieldValue.value) {
        println("is valid call")
        billAmt(textFieldValue.value.trim().toString())
        textFieldValue.value.trim().isNotEmpty()
    }

    var keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(150.dp),
        shadowElevation=0.dp,
        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
        border = BorderStroke(color = Color.Gray, width = 1.dp)
    ) {

        Column(verticalArrangement = Arrangement.Center,  ) {
            InputFiled(

                valueState = textFieldValue,
                labelId = "Enter Bill",
                enable = true,
                onAction = KeyboardActions {
                    if (!isValid) return@KeyboardActions
                    println("keyboard is hide")
                    keyboardController?.hide()
                }, modifier = Modifier
                    .padding(all = 4.dp)
                    .fillMaxWidth()
            )

            if(isValid){
                Row {
                    Text(text = "Split",modifier= Modifier.align(alignment = Alignment.CenterHorizontally))
                }
            }
        }

    }
}
 