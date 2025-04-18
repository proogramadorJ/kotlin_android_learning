package com.pedrodev.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pedrodev.calculadora.ui.theme.CalculadoraTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            CalculadoraTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    var value1 by remember { mutableStateOf("") }
    var value2 by remember { mutableStateOf("") }
    var result by remember { mutableFloatStateOf(0.0f) }

    Column(modifier = Modifier.padding(10.dp)) {
        Text("Simple Calculator", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        Spacer(Modifier.height(20.dp))
        TextFiledComposable("Num 1", value1) { value1 = it }
        Spacer(modifier = Modifier.height(15.dp))
        TextFiledComposable("Num 2", value2) { value2 = it }

        Spacer(modifier = Modifier.height(15.dp))

        ButtonComposable("+") { result = value1.toFloat() + value2.toFloat() }
        ButtonComposable("-") { result = value1.toFloat() - value2.toFloat() }
        ButtonComposable("*") { result = value1.toFloat() * value2.toFloat() }
        ButtonComposable("/") { result = value1.toFloat() / value2.toFloat() }

        Text(
            result.toString(),
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )


    }
}

@Composable
fun ButtonComposable(text: String, action: () -> Unit) {
    Button(
        onClick = action,
        modifier = Modifier.fillMaxWidth()

    ) {
        Text(text)
    }
}


@Composable
fun TextFiledComposable(label: String, value: String, changeValue: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = value,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        onValueChange = changeValue,
        label = { Text(label) }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculadoraTheme {
        MainContent()
    }
}