package com.jungbang.transitionmanager.sys.net

class ProtocolFactory
{
    companion object {
        private var mInstance: ProtocolFactory? = null

        @JvmStatic
        fun getInstance(): ProtocolFactory = mInstance ?:
        synchronized(this) {
            mInstance ?: ProtocolFactory().also {
                mInstance = it
            }
        }

        @JvmStatic
        fun <T : AbstractHttpProtocol<*>> create(clazz: Class<T>): T {
            val t: T = clazz.newInstance()

            return t
        }
    }
}