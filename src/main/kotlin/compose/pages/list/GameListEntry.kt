package compose.pages.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.services.games.dto.GameDto

@Composable
fun GameListEntry(item: GameDto) {
    MaterialTheme {
        Card(
            modifier = Modifier.padding(10.dp).fillMaxWidth()
        ) {
            Column {
                Text(text = item.name)
                Row {
                    Text("Target Price: ${item.targetPrice}")
                    Text("Target Discount: ${item.targetDiscount}")
                }
                if (item.steamId != null) {
                    Row {
                        Text("Steam ID: ${item.steamId}")
                    }
                }
            }
        }
    }
}