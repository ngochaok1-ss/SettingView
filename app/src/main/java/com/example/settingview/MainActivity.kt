package com.example.settingview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.settingview.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    val TAG = "MainActivityWidgetSetting"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = AppTheme.colorScheme.background) {

                    var slider by rememberSaveable { mutableFloatStateOf(50f) }
                    var radioButon by rememberSaveable { mutableStateOf(getString(R.string.match_settings)) }
                    var titleNote by rememberSaveable { mutableStateOf("Title Note") }
                    var style by rememberSaveable { mutableIntStateOf(Constants.STYLE_BOUND_1) }
                    var background by rememberSaveable { mutableIntStateOf(0) }
                    WidgetSettingsLayout(modifier = Modifier.padding(start = 15.dp,end = 15.dp),
                        onSelectedRadioButton = radioButon,
                        slider = slider,
                        titleNote = titleNote,
                        style = style,
                        background = background){ value, type ->
                            when(type){
                                Constants.EVENT_RADIO_BUTTON_CLICK -> {
                                    radioButon = value
                                    backGroundChange()
                                }

                                Constants.EVENT_SLIDER_CHANGE -> {
                                    slider = value.toFloat()
                                    transparentChange()
                                }

                                Constants.EVENT_CHANGE_NOTE_TITLE -> {
                                    titleNote = "Title Change"
                                    titleNoteChanged()
                                }

                                Constants.EVENT_STYLE_CHANGE -> {
                                    style = value.toInt()
                                }
                            }

                    }
                }
            }
        }
    }

    private fun titleNoteChanged() {

    }

    private fun transparentChange() {

    }

    private fun backGroundChange() {

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }
}
