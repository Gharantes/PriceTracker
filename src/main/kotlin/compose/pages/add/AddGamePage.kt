package compose.pages.add

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*

@Composable
fun AddGamePage () {
    var name by remember { mutableStateOf("") }
    var targetPrice by remember { mutableStateOf("") }
    var targetDiscount by remember { mutableStateOf("") }
    var steamId by remember { mutableStateOf("") }

    MaterialTheme {
        Column {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") }
            )
            OutlinedTextField(
                value = targetPrice,
                onValueChange = { targetPrice = it },
                label = { Text("Target Price") }
            )
            OutlinedTextField(
                value = targetDiscount,
                onValueChange = { targetDiscount = it },
                label = { Text("Target Discount") }
            )
            OutlinedTextField(
                value = steamId,
                onValueChange = { steamId = it },
                label = { Text("Steam ID") }
            )
        }
    }
}