package com.test.triangle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.jvm.Throws

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var firstСorner by remember {
                mutableStateOf("")
            }
            var secondСorner by remember {
                mutableStateOf("")
            }
            var thirdСorner by remember {
                mutableStateOf("")
            }
            var answer by remember {
                mutableStateOf<String?>(null)
            }

            if (answer == null){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Введите длины сторон треугольника", fontSize = 18.sp, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.size(12.dp))
                    Column {
                        OutlinedTextField(
                            value = firstСorner,
                            onValueChange = {
                                firstСorner = it
                            }
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        OutlinedTextField(
                            value = secondСorner,
                            onValueChange = {
                                secondСorner = it
                            }
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        OutlinedTextField(
                            value = thirdСorner,
                            onValueChange = {
                                thirdСorner = it
                            }
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        Button(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            onClick = {
                                answer = checkTriangleType(
                                    firstСorner,
                                    secondСorner,
                                    thirdСorner
                                )
                            }
                        ){
                            Text(text = "Проверить", fontSize = 18.sp)
                        }
                    }
                }
            }else{
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = answer!!, textAlign = TextAlign.Center,fontSize = 18.sp)
                    Spacer(modifier = Modifier.size(12.dp))
                    Button(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = {
                            answer = null
                            firstСorner = ""
                            secondСorner = ""
                            thirdСorner = ""
                        }
                    ){
                        Text(text = "Начать снова")
                    }
                }
            }
        }
    }
}

fun checkTriangleType(first: String, second: String, third: String): String {
    try {
        val firstCorner = first.toIntOrNull()
        val secondCorner = second.toIntOrNull()
        val thirdCorner = third.toIntOrNull()

        if (firstCorner == null || secondCorner == null || thirdCorner == null) {
            return "Некорректный ввод: все стороны должны быть целыми числами."
        }

        if (firstCorner <= 0 || secondCorner <= 0 || thirdCorner <= 0) {
            return "Некорректный ввод: стороны треугольника должны быть положительными числами."
        }

        if (firstCorner + secondCorner <= thirdCorner ||
            firstCorner + thirdCorner <= secondCorner ||
            secondCorner + thirdCorner <= firstCorner) {
            return "Такой треугольник не существует."
        }

        return when {
            firstCorner == secondCorner && secondCorner == thirdCorner -> "Равносторонний треугольник"
            firstCorner == secondCorner || secondCorner == thirdCorner || firstCorner == thirdCorner -> "Равнобедренный треугольник"
            else -> "Разносторонний треугольник"
        }
    }catch (e: Throwable){
        return "Числа выходят за int"
    }
}
