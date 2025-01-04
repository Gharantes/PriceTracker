package compose.pages.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import compose.constants.DarkBackground2
import compose.constants.LightText
import compose.models.AddGameViewModel

@Composable
fun AddGamePage(
    addGameViewModel: AddGameViewModel
) {
    val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = LightText,
        unfocusedBorderColor = Color.Gray,
        focusedLabelColor = LightText,
        unfocusedLabelColor = Color.LightGray,
        textColor = LightText,
        backgroundColor = DarkBackground2
    )
    val commonTextFieldModifier = Modifier.background(DarkBackground2).fillMaxWidth()

    MaterialTheme {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                modifier = commonTextFieldModifier,
                value = addGameViewModel.name,
                onValueChange = { addGameViewModel.name = it },
                label = { Text("Name") },
                colors = textFieldColors
            )
            OutlinedTextField(
                modifier = commonTextFieldModifier,
                value = addGameViewModel.targetPrice,
                onValueChange = { addGameViewModel.targetPrice = it },
                label = { Text("Target Price") },
                colors = textFieldColors
            )
            OutlinedTextField(
                modifier = commonTextFieldModifier,
                value = addGameViewModel.targetDiscount,
                onValueChange = { addGameViewModel.targetDiscount = it },
                label = { Text("Target Discount") },
                colors = textFieldColors
            )
            OutlinedTextField(
                modifier = commonTextFieldModifier,
                value = addGameViewModel.steamId,
                onValueChange = { addGameViewModel.steamId = it },
                label = { Text("Steam ID") },
                colors = textFieldColors
            )
        }
    }
}