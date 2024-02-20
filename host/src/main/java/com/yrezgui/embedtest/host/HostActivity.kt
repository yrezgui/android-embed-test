package com.yrezgui.embedtest.host

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yrezgui.embedtest.host.ui.theme.EmbedTestTheme

class HostActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isPickerOpened by remember { mutableStateOf(false) }
            val launchIntent = rememberLauncherForActivityResult(StartActivityForResult()) {
                isPickerOpened = false
            }

            val pickerBottomPaddingDp by animateDpAsState(
                (if (isPickerOpened) PICKER_BOTTOM_PADDING else 0).dp,
                label = "Embedded picker padding"
            )
            val pickerPadding = Modifier.padding(bottom = pickerBottomPaddingDp)

            EmbedTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val bigList = List(100) {
                        "Item #$it"
                    }

                    LazyColumn(Modifier.then(pickerPadding)) {
                        item {
                            Button(onClick = {
                                isPickerOpened = true
                                launchIntent.launch(createPickerIntent())
                            }) {
                                Text("Open picker")
                            }
                        }
                        item {
                            TextField(value = "Host", onValueChange = {})
                        }
                        items(bigList) {
                            ListItem(headlineContent = { Text(it) })
                        }
                    }
                }
            }
        }
    }

    private fun createPickerIntent(): Intent {
        return Intent("embed.PICKER").apply {
            type = "image/*"
            putExtra("height", PICKER_BOTTOM_PADDING)
        }
    }

    companion object {
        const val PICKER_BOTTOM_PADDING = 300
    }
}