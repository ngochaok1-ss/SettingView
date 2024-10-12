package com.example.settingview

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.settingview.Utils.getBottomEndOfStyle
import com.example.settingview.Utils.getBottomStartOfStyle
import com.example.settingview.Utils.getTopEndOfStyle
import com.example.settingview.Utils.getTopStartOfStyle
import com.example.settingview.ui.theme.AppTheme

@Composable
fun WidgetSettingsLayout(modifier: Modifier = Modifier,
                         background : Int,
                         onSelectedRadioButton : String,
                         slider : Float,
                         titleNote : String,
                         style : Int,
                         onSelected : (optionSelected : String, eventType : Int) -> Unit){

    Column(modifier = modifier
        .fillMaxSize()
        .padding(start = 5.dp, end = 5.dp)
        .windowInsetsPadding(WindowInsets.systemBars)) {

        TitleApp(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()){}

        PreviewLayout(modifier = Modifier
            .fillMaxWidth()
            .weight(2.5f),
            background,
            titleNote = titleNote,
            transparent = slider,
            style = style)


        MainSettingsLayout(modifier = Modifier
            .fillMaxWidth()
            .weight(4f),
            onSelectedRadioButton = onSelectedRadioButton,
            slider = slider,
            titeNote = titleNote,
            style = style
            ){ value,type ->
                onSelected(value,type)
            }

        NavigationBottom()
    }
}



@Composable
fun MainSettingsLayout(modifier: Modifier,
                       onSelectedRadioButton : String,
                       slider : Float,
                       titeNote : String,
                       style : Int,
                       onSelected : (optionSelected : String, eventType : Int) -> Unit) {
    Column(modifier = modifier.verticalScroll(rememberScrollState()) ) {

        TitleNote(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 12.dp),
            titleNote = titeNote){ value, eventType ->
                onSelected(value, eventType)
        }

        StyleSettings(style = style){ value, type ->
            onSelected(value, type)
        }

        Text(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp),
            text = stringResource(id = R.string.background),
            fontSize = 16.sp, color = Color(0xFC696969), fontWeight = FontWeight.Bold )
        BackgroundSettings(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
            onSelectedRadioButton){ value, type ->
                onSelected(value,type)
        }

        TransparentSettings(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
            slider){value, type->
                onSelected(value,type)
        }
    }
}

@Composable
fun StyleSettings(modifier: Modifier = Modifier, style : Int, onChanged : (optionSelect : String, eventType : Int) -> Unit ) {
    val styles = mutableListOf(Constants.STYLE_BOUND_1, Constants.STYLE_BOUND_2,Constants.STYLE_BOUND_3,Constants.STYLE_BOUND_4)
    Text(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(12.dp),
        text = stringResource(id = R.string.style),
        fontSize = 16.sp, color = Color(0xFC696969), fontWeight = FontWeight.Bold )
    Box(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clip(RoundedCornerShape(16.dp))
        .background(AppTheme.colorScheme.onbackground)
        .horizontalScroll(rememberScrollState())){

        Row (modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(20.dp), horizontalArrangement = Arrangement.spacedBy(20.dp)){
            repeat(styles.size){
                StyleItem(typeStyle = styles[it]) { value, type ->
                    onChanged(value.toString(), type)
                }
            }
        }
    }
}

@Composable
fun PreviewLayout(modifier: Modifier,background : Int, titleNote: String, transparent : Float, style : Int) {
    Log.d("MainActivityWidgetSetting", "preview layout Changed")
    Box(modifier = modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(16.dp))
        .background(Color(0xFF76A7AD)),
        contentAlignment = Alignment.Center){
        val parentSize = LocalDensity.current.run { 100.dp.toPx() }
        Column(modifier = Modifier.size((0.9f * parentSize).dp, (0.6f * parentSize).dp)
            .clip(RoundedCornerShape(
                topStart = getTopStartOfStyle(style).dp,
                topEnd = getTopEndOfStyle(style).dp,
                bottomStart = getBottomStartOfStyle(style).dp,
                bottomEnd = getBottomEndOfStyle(style).dp
            ))
            .background(AppTheme.colorScheme.background)
            .alpha(1 - transparent / 100)) {
            if(titleNote.isNotBlank()) {
                TitleNoteInPreview(titleNote)
            }
            NoteContent(Modifier.fillMaxWidth().wrapContentHeight())
        }
    }
}

@Composable
fun NavigationBottom() {
    Row (modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween){
        NormalButton(modifier = Modifier
            .wrapContentHeight()
            .weight(1f),text = stringResource(id = R.string.cancel))
        NormalButton(modifier = Modifier
            .wrapContentHeight()
            .weight(1f),text =stringResource(id = R.string.save))
    }
}