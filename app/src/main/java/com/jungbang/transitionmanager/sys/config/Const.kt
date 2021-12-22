package com.jungbang.transitionmanager.sys.config

import android.os.Environment
import java.util.*

object Const
{
    interface NETWORK {
        enum class APP(private val domain: String, private val path: String) {
            SERVER("https://n3e0gxsdkg.execute-api.ap-northeast-2.amazonaws.com/GreenAPI/", "");

            fun getDomain() = domain

            fun getPath() = path

            fun getUrl() = domain + path
        }
    }
}