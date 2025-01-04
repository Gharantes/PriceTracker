package compose.pages.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import compose.constants.DarkBackground1
import compose.constants.DarkBackground2
import compose.constants.LightText
import core.services.games.dto.GameDto

@Composable
fun GameListEntry(item: GameDto) {
    MaterialTheme {
        Surface(
            color = DarkBackground1,
            elevation = 10.dp,
            modifier = Modifier
        ) {
            Card(
                backgroundColor = DarkBackground2,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row {
                    Column (
                        modifier = Modifier.padding(10.dp),
                    ) {
                        Text(
                            color = LightText,
                            text = item.name
                        )
                        Row {
                            Text(
                                color = LightText,
                                text = "Target Price: ${item.targetPrice}"
                            )
                            Text(
                                color = LightText,
                                text = "Target Discount: ${item.targetDiscount}"
                            )
                        }
                        if (item.steamId != null) {
                            Row {
                                Text(
                                    color = LightText,
                                    text = "Steam ID: ${item.steamId}"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}