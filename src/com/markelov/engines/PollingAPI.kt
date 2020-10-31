package com.markelov.engines

import iris.json.plain.IrisJsonItem

interface PollingAPI : Runnable {
    fun getIdByToken(): Int
    fun getServer(): IrisJsonItem
    fun makeLongRequest(server: Map<String, Any?>): IrisJsonItem
    fun runPolling()
    fun stop()
    override fun run()

}