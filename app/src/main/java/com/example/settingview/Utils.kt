package com.example.settingview

import com.example.settingview.Constants.STYLE_BOUND_1
import com.example.settingview.Constants.STYLE_BOUND_2
import com.example.settingview.Constants.STYLE_BOUND_3
import com.example.settingview.Constants.STYLE_BOUND_4

object Utils {
    fun getTopStartOfStyle(style : Int) : Int {
        if(style == STYLE_BOUND_4) return 0
        return 16
    }

    fun getTopEndOfStyle(style: Int) : Int {
        if(style == STYLE_BOUND_1 || style == STYLE_BOUND_3) return 0
        return 16
    }

    fun getBottomStartOfStyle(style: Int) : Int {
        if( style == STYLE_BOUND_3) return 0
        return 16
    }

    fun getBottomEndOfStyle(style: Int) : Int {
        if( style == STYLE_BOUND_2 || style == STYLE_BOUND_4 ) return 0
        return 16
    }
}