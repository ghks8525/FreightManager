package com.jungbang.transitionmanager.sys.net

import com.jungbang.freightmanager.Utils.Trace

interface HttpResponsable<in RES>
{
    fun onResponse(res: RES)

    fun onFailure(nError: Int, strMsg: String) {
        Trace.debug(">> onFailure() $strMsg[$nError]")
    }
}