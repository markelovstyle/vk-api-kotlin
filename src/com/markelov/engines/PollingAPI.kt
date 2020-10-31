package com.markelov.engines

import com.markelov.http.HTTPRequest
import iris.json.plain.IrisJsonItem
import java.util.logging.Logger

abstract class PollingAPI: Runnable {
    protected var stopped = false
    protected val requester = HTTPRequest()

    companion object {
        val logger: Logger = Logger.getLogger("VK-API")
    }

    abstract fun getIdByToken(): Int
    abstract fun getServer(): IrisJsonItem
    abstract fun makeLongRequest(server: Map<String, Any?>): IrisJsonItem
    abstract override fun run()

    fun runPolling() {
        /**
         * Неблокирующий запуск обработки событий
         */
        logger.info("Polling successfully started.")
        val thread = Thread(this)
        thread.start()

    }
    fun stop() {
        logger.info("Stopping polling...")
        this.stopped = true
    }
}