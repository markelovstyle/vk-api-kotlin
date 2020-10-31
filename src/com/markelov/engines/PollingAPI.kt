package com.markelov.engines

import com.markelov.http.HTTPRequest
import iris.json.plain.IrisJsonItem

abstract class PollingAPI: Runnable {
    protected var stopped = false
    protected val requester = HTTPRequest()

    abstract fun getIdByToken(): Int
    abstract fun getServer(): IrisJsonItem
    abstract fun makeLongRequest(server: Map<String, Any?>): IrisJsonItem
    abstract override fun run()

    fun runPolling() {
        /**
         * Неблокирубщий запуск обработки событий
         */
        val thread = Thread(this)
        thread.start()

    }
    fun stop() {
        println("Stopping polling...")
        this.stopped = true
    }
}