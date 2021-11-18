package com.jungbang.freightmanager.Utils

import android.util.Log

class Trace {
    companion object{
        fun debug(strMsg: String?){
            var strFileName = Thread.currentThread().stackTrace[4].fileName
            val nLineNumber = Thread.currentThread().stackTrace[4].lineNumber
            val strLog = String.format("%-20s %5d  %s\n", strFileName, nLineNumber, strMsg)

            Log.d(Thread.currentThread().name, strLog)
        }
    }
}