package com.example.settingview

import android.util.Log
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.settingview.ui.theme.AppTheme

@Composable
fun NormalButton(modifier: Modifier,text : String){
    Button(modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = AppTheme.colorScheme.primary),
        onClick = {}
    )
    {
        Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun M_Divider(){
    Divider(modifier = Modifier.padding(start = 30.dp,end =  30.dp), color = Color(0xFCECECEC), thickness = 1.dp)
}

@Composable
fun StyleItem(typeStyle : Int, onClick : (typeStyle : Int, eventType : Int) -> Unit) {
    Box(modifier = Modifier
        .width(75.dp)
        .height(100.dp)
        .clip(
            RoundedCornerShape(
                topStart = Utils.getTopStartOfStyle(typeStyle).dp,
                topEnd = Utils.getTopEndOfStyle(typeStyle).dp,
                bottomEnd = Utils.getBottomEndOfStyle(typeStyle).dp,
                bottomStart = Utils.getBottomStartOfStyle(typeStyle).dp
            )
        )
        .background(Color(0xFCB8B5B5))
        .clickable { onClick(typeStyle, Constants.EVENT_STYLE_CHANGE) }
    )
}

@Composable
fun BackgroundSettings(modifier: Modifier = Modifier, selectedOption : String,
                       onSelect : (selectOptions : String, eventType : Int) -> Unit) {
    Log.d("MainActivityWidgetSetting", "radio Changed")
    Column(modifier= modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clip(RoundedCornerShape(16.dp))
        .background(AppTheme.colorScheme.onbackground)) {
        val options = listOf(
            stringResource(id = R.string.match_settings),
            stringResource(id = R.string.light),
            stringResource(id = R.string.dark)
        )

        options.forEach{ option ->
            val interactionSource = remember{ MutableInteractionSource() }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .clickable(indication = null, interactionSource = interactionSource)
                {
                    interactionSource.tryEmit(PressInteraction.Press(Offset.Zero))
                    onSelect(option, Constants.EVENT_RADIO_BUTTON_CLICK)
                },
                verticalAlignment = Alignment.CenterVertically)
                {

                RadioButton(selected = selectedOption == option,
                    onClick = {
                        onSelect(option, Constants.EVENT_RADIO_BUTTON_CLICK)
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = AppTheme.colorScheme.secondary,
                        unselectedColor = Color(0xFCD1D1D1)
                    ),
                    modifier = Modifier
                        .indication(interactionSource, rememberRipple(bounded = true, radius = 20.dp))
                )

                Text(
                    text = option,
                    modifier = Modifier.padding(start = 12.dp),
                    fontSize = 18.sp,
                    color = AppTheme.colorScheme.onPrimary
                )
            }
            if(option != stringResource(id = R.string.dark)){
                M_Divider()
            }
        }
    }
}

@Composable
fun TitleApp(modifier: Modifier = Modifier,onClick : () -> Unit){
    Row(modifier = modifier
        .background(Color.Transparent)
        .padding(top = 12.dp, bottom = 12.dp, start = 16.dp)) {
        Text(text = "Widget Setting", color = AppTheme.colorScheme.onPrimary, fontSize = 30.sp, fontStyle = FontStyle.Normal, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun TitleNote(modifier: Modifier, titleNote: String, onChangeTitle : (titleNote : String, eventType : Int) -> Unit ) {
    Row(modifier = modifier
        .clip(RoundedCornerShape(24.dp))
        .background(AppTheme.colorScheme.onbackground)
    ) {
        Box(modifier = Modifier
            .wrapContentHeight()
            .weight(1f)
            .padding(12.dp)) {

            Text(
                modifier = Modifier.wrapContentHeight(),
                text = titleNote,
                maxLines = 1,
                fontSize = 16.sp,
                color = AppTheme.colorScheme.onPrimary
            )
        }
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .clip(RoundedCornerShape(24.dp))
                .clickable { onChangeTitle(titleNote, Constants.EVENT_CHANGE_NOTE_TITLE) }
                .indication(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(
                        bounded = true,
                        color = AppTheme.colorScheme.background,
                        radius = 16.dp
                    ),
                )
                .padding(12.dp),
        ) {
            Text(text = stringResource(id = R.string.change),
                color = AppTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun NoteContent(modifier: Modifier){
    LazyColumn(modifier = modifier.padding(start = 10.dp, end = 10.dp)) {

    }
}

@Composable
fun TitleNoteInPreview(titleNote: String){
    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()) {
        Text(text = titleNote, fontSize = 12.sp, modifier = Modifier
            .weight(1f)
            .padding(12.dp), color = AppTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold)
    }
}



@Composable
fun TransparentSettings(modifier: Modifier = Modifier,sliderPosition : Float,
                        onChanged : (value : String, eventType : Int) -> Unit) {
    Log.d("MainActivityWidgetSetting", "transparent Changed")
    Column(modifier = modifier) {
        Text(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp),
            text = stringResource(id = R.string.transparent),
            fontSize = 16.sp, color = Color(0xFC696969), fontWeight = FontWeight.Bold )
        Box(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .clip(
                RoundedCornerShape(16.dp)
            )
            .background(AppTheme.colorScheme.onbackground)
            .padding(start = 30.dp, end = 30.dp)
        ) {
            Slider(
                value = sliderPosition,
                onValueChange = { onChanged(it.toString(), Constants.EVENT_SLIDER_CHANGE) },
                valueRange = 0f..100f,
                steps = 1,
                colors = SliderDefaults.colors(
                    thumbColor = Color(0xFCE4E3E3),
                    activeTrackColor = Color(0xFF76A7AD),
                    inactiveTrackColor = Color(0xFFE3E8E9)
                ),
                modifier = Modifier.background(AppTheme.colorScheme.onbackground)
            )
        }
    }
}