package com.example.labwork18

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.collection.emptyLongSet
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.labwork18.ui.theme.LabWork18Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabWork18Theme {
                Authorization()
                //DisplayGoods()
                //OrderedGoods()
                //Info()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Authorization(){
    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val greeting = remember { mutableStateOf("") }
    Column() {
        TextField(
            value = login.value,
            singleLine = true,
            label = { Text("Логин")},
            onValueChange = { newValue ->
                val filteredText = newValue.filterNot {
                    it == '\n' || it == ' ' || it == '\t'
                }
                login.value = filteredText
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )
        TextField(
            value = password.value,
            singleLine = true,
            label = { Text("Пароль") },
            onValueChange = { newValue ->
                val filteredText = newValue.filterNot {
                    it == '\n' || it == ' ' || it == '\t'
                }
                password.value = filteredText
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )
        Row {
            Button(onClick = { greeting.value = "${login.value}, вы авторизованы" }, shape = RoundedCornerShape(15.dp)) {
                Text(text = "Авторизоваться")
            }
            OutlinedButton(onClick = { greeting.value = "${login.value}, вы авторизованы" }) {
                Text(text = "Авторизоваться")
            }
            TextButton(onClick = { greeting.value = "${login.value}, вы авторизованы" }) {
                Text(text = "Авторизоваться")
            }
        }
        Text("$greeting")
    }
}

@Composable
fun DisplayGoods(){
    val label = remember{mutableStateOf("Добавить в корзину")}
    val color = remember { mutableStateOf(Color.White) }
    val amount = remember { mutableStateOf(0) }
    Column {
        Text("Name of tovar")
        Text("Price of tovar")
        Button(onClick = {label.value = "Перейти в корзину"; color.value = Color.Blue; amount.value++ },
            colors = ButtonDefaults.buttonColors(containerColor = color.value),
            border = BorderStroke(3.dp, Color.Black)) {
            Image(bitmap = ImageBitmap.imageResource(R.drawable.shoppingcart), null)
            Spacer(Modifier.width(10.dp))
            Text(text = label.value)
        }
        Text("${amount.value}")
    }
}

@Composable
fun OrderedGoods(){
    val amount = remember { mutableStateOf(1) }
    val totalPrice = remember { mutableStateOf(0) }
    val isAddEnabled = remember { mutableStateOf(true) }
    val isSubtEnabled = remember { mutableStateOf(false) }
    val price = 100
    Column {
        Text("Name of tovar")
        Text("$price")
        Row {
            IconButton(onClick = {
                amount.value++
                totalPrice.value = price * amount.value
                if(amount.value == 10)
                    isAddEnabled.value = false
                else
                    isAddEnabled.value = true
            }, enabled = isAddEnabled.value) {
                Icon(Icons.Filled.Add, contentDescription = "Добавить товар")
            }
            IconButton(onClick = {
                amount.value--
                totalPrice.value = price * amount.value
                if(amount.value == 1)
                    isSubtEnabled.value = false
                else
                    isSubtEnabled.value = true
            }, enabled = isSubtEnabled.value) {
                Icon(Icons.Filled.Delete, contentDescription = "Уменьшить товар")
            }
        }
        Text("${amount.value}")
        Text("${totalPrice.value}")

        Row(Modifier.padding(25.dp)) {
            val isAdded = remember { mutableStateOf(false) }
            ExtendedFloatingActionButton(
                icon = { if(isAdded.value) Icons.Filled.Delete else Icons.Filled.Add},
                text = { Text(text = if (isAdded.value) "Удалить" else "Добавить") },
                onClick = {isAdded.value = !isAdded.value}
            )
        }
    }
}

@Composable
fun Info(){
    Column {
        FloatingActionButton(onClick = { }) {
            Icon(Icons.Filled.Call, contentDescription = "Совершить звонок")
        }
        FloatingActionButton(onClick = { }) {
            Icon(Icons.Filled.Email, contentDescription = "Отправить Email")
        }
    }
}