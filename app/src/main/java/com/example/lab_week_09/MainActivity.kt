package com.example.lab_week_09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab_week_09.ui.theme.LAB_WEEK_09Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LAB_WEEK_09Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var text by remember { mutableStateOf("") }
                    var items by remember { mutableStateOf(listOf("Tanu", "Tina", "Tono")) }

                    Home(
                        items = items,
                        text = text,
                        onTextChange = { text = it },
                        onAddItem = {
                            if (text.isNotBlank()) {
                                items = items + text
                                text = "" // Clear input after adding
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Home(
    items: List<String>,
    text: String,
    onTextChange: (String) -> Unit,
    onAddItem: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(16.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Input section
        item {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = stringResource(id = R.string.enter_item))

                TextField(
                    value = text,
                    onValueChange = onTextChange,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text // Changed from Number to Text
                    ),
                    label = { Text("Enter a name") }
                )

                Button(onClick = onAddItem) {
                    Text(text = stringResource(id = R.string.button_click))
                }

                Text(
                    text = stringResource(id = R.string.list_title),
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }

        // List section
        items(items) { item ->
            Column(
                modifier = Modifier.padding(vertical = 4.dp).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = item)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    LAB_WEEK_09Theme {
        Home(
            items = listOf("Tanu", "Tina", "Tono"),
            text = "New Item",
            onTextChange = {},
            onAddItem = {}
        )
    }
}
