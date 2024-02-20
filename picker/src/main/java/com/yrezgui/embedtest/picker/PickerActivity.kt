package com.yrezgui.embedtest.picker

import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yrezgui.embedtest.picker.ui.theme.EmbedTestTheme

class PickerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val params = window.attributes
        val height = intent.getIntExtra("height", 300)
        params.gravity = Gravity.BOTTOM or Gravity.START or Gravity.END
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL

        window.attributes = params

        setContent {
            EmbedTestTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Column {
                        Text("Embedded Picker")
                        TextField(value = "Picker", onValueChange = {})
                    }
                }
            }
        }
    }
}