package com.jungbang.transitionmanager.sys.net

interface HttpTransactionCallback
{
    fun transactionBegin(protocol: HttpRequestable)

    fun transactionEnd(protocol: HttpRequestable)
}