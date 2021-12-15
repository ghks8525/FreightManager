package com.jungbang.transitionmanager.sys.utils

import android.content.Context
import android.view.WindowManager


class Utils {
    companion object {
        fun getDpToPx(context: Context) {
        }

        fun getPxToDp(context: Context, px:Float): Int {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

            return (px / context.resources.displayMetrics.density).toInt()
        }
    }
}