package com.jungbang.transitionmanager.sys.utils

import android.content.Context
import android.content.SharedPreferences
import android.view.WindowManager


class Utils {
    companion object {
        fun getDpToPx(context: Context) {
        }

        fun getPxToDp(context: Context, px:Float): Int {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

            return (px / context.resources.displayMetrics.density).toInt()
        }

        fun setPrefString(mContext: Context, key:String, value:String){
            val prefs: SharedPreferences = mContext.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)
            prefs.edit().putString(key, value)
        }

        fun getPrefString(mContext: Context, key:String): String? {
            val prefs: SharedPreferences = mContext.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)
            return prefs.getString(key, null)
        }

        fun setPrefInt(mContext: Context, key:String, value:Int){
            val prefs: SharedPreferences = mContext.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)
            prefs.edit().putInt(key, value)
        }

        fun getPrefInt(mContext: Context, key:String): Int {
            val prefs: SharedPreferences = mContext.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)
            return prefs.getInt(key, 0)
        }
    }
}