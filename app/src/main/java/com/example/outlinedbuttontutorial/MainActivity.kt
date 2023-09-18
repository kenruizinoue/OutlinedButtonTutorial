package com.example.outlinedbuttontutorial

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.outlinedbuttontutorial.ui.theme.OffRed
import com.example.outlinedbuttontutorial.ui.theme.OnGreen
import com.example.outlinedbuttontutorial.ui.theme.OutlinedButtonTutorialTheme
import com.example.outlinedbuttontutorial.ui.theme.Purple80

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OutlinedButtonTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        SwitchButton(
                            onText = "Yes",
                            offText = "No"
                        )
                        SwitchButton(
                            defaultState = true
                        )
                        SwitchButton(
                            onColor = OffRed,
                            offColor = OnGreen
                        )
                        SwitchButton { isOn ->
                            Toast.makeText(
                                this@MainActivity,
                                "Switch is ${if (isOn) "Yes" else "No"}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        SwitchButton(
                            defaultState = true,
                            onText = "Yes",
                            offText = "Off",
                            onColor = OnGreen,
                            offColor = Purple80,
                        ) { isOn ->
                            Toast.makeText(
                                this@MainActivity,
                                "SwitchButton is ${if (isOn) "Yes" else "Off"}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SwitchButton(
    defaultState: Boolean = false,
    onText: String = "On",
    offText: String = "Off",
    onColor: Color = OnGreen,
    offColor: Color = OffRed,
    doWhenClicked: (Boolean) -> Unit = {},
) {
    val isOn = remember { mutableStateOf(defaultState) }
    val buttonText = rememberUpdatedState(if (isOn.value) onText else offText)
    val switchColor = rememberUpdatedState(if (isOn.value) onColor else offColor)
    OutlinedButton(
        onClick = {
            isOn.value = isOn.value.not()
            doWhenClicked(isOn.value)
        },
        border = BorderStroke(2.dp, switchColor.value),
    ) {
        Text(
            text = buttonText.value,
            fontWeight = FontWeight.Bold,
            color = switchColor.value
        )
    }
}
