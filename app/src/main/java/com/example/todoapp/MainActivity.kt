package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.ui.theme.ToDoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}


@Composable
fun App() {
    var item by remember {
        mutableStateOf("")
    }

    val items = mutableListOf<String>()

    ToDoAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.title),
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )

                Divider()

                Row(modifier = Modifier.align(CenterHorizontally)) {
                    OutlinedTextField(
                        value = item,
                        onValueChange = { item = it },
                        label = { Text(text = stringResource(id = R.string.new_item)) },
                        modifier = Modifier.padding(8.dp)
                    )

                    Button(
                        onClick = {
                            if (item.isNotEmpty()) {
                                items.add(item)
                                item = ""
                            }
                        },
                        modifier = Modifier.padding(vertical = 20.dp, horizontal = 4.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = colorResource(id = R.color.teal_700),
                            contentColor = Color.White
                        )

                    ) {
                        Text(
                            text = stringResource(id = R.string.save)
                        )
                    }
                }

                Divider()

                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    for (listItem in items) {
                        ToDoListItem(name = listItem)
                    }
                }
            }
        }
    }
}


@Composable
fun ToDoListItem(name: String) {
    var itemSelected by remember {
        mutableStateOf(false)
    }

    var color = colorResource(id = R.color.white)

    if (itemSelected) color = colorResource(id = R.color.teal_700)

    Surface(
        color = color,
        modifier = Modifier.clickable {
            itemSelected = !itemSelected
        }
    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(6.dp)
        )

        Divider()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    App()
}