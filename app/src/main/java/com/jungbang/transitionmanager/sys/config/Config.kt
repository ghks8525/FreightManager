package com.jungbang.transitionmanager.sys.config

class Config {
    companion object{
        const val SUPPORT_DEBUG = true
        fun getAppServer(): Const.NETWORK.APP {
            return Const.NETWORK.APP.SERVER
        }
    }
}