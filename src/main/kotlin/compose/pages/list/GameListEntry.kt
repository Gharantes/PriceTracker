package compose.pages.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import compose.constants.DarkBackground1
import compose.constants.DarkBackground2
import compose.constants.LightText
import core.services.games.dto.GameDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun GameListEntry(
    item: GameDto,
    deleteGame: (Long) -> Unit
) {
    var secondaryIsOpen by remember { mutableStateOf(false) }

    MaterialTheme {
        Column {
            Surface(
                color = DarkBackground1,
                elevation = 10.dp,
                modifier = Modifier
            ) {
                Card(
                    backgroundColor = DarkBackground2,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Column (
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
                        Spacer(
                            modifier = Modifier.weight(1f)
                        )
                        Button(onClick = {
                            secondaryIsOpen = !secondaryIsOpen
                        }) {
                            Icon(
                                if (secondaryIsOpen) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
            if (secondaryIsOpen) {
                Box (
                    modifier = Modifier.fillMaxWidth().height(40.dp).background(DarkBackground2)
                ) {
                    Button(onClick = {
                        deleteGame(item.id)
                    }) {
                        Icon(Icons.Filled.Delete, contentDescription = null)
                    }
                }
            }
        }

    }
}