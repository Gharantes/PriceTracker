package compose.pages.add

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import compose.models.AddGameViewModel

@Composable
fun AddGamePage(
    addGameViewModel: AddGameViewModel
) {
    MaterialTheme {
        Column {
            OutlinedTextField(
                value = addGameViewModel.name,
                onValueChange = { addGameViewModel.name = it },
                label = { Text("Name") }
            )
            OutlinedTextField(
                value = addGameViewModel.targetPrice,
                onValueChange = { addGameViewModel.targetPrice = it },
                label = { Text("Target Price") }
            )
            OutlinedTextField(
                value = addGameViewModel.targetDiscount,
                onValueChange = { addGameViewModel.targetDiscount = it },
                label = { Text("Target Discount") }
            )
            OutlinedTextField(
                value = addGameViewModel.steamId,
                onValueChange = { addGameViewModel.steamId = it },
                label = { Text("Steam ID") }
            )
        }
    }
}