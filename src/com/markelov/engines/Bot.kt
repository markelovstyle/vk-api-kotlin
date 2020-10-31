package com.markelov.engines

import com.markelov.api.Api
import com.markelov.utils.BOT_POLLING_URL
import iris.json.plain.IrisJsonItem

class Bot(
    private val api: Api,
    private val wait: Int = 15,
    private val rpsDelay: Int = 0,
    groupId: Int = 0
) : PollingAPI() {

    private val groupId = if (groupId == 0) getIdByToken() else groupId

    override fun getIdByToken(): Int {
        logger.info("Making API request groups.getById to get group_id")
        val request = api.request("groups.getById", null)
        return request["response"][0]["id"].asInt()
    }

    override fun getServer(): IrisJsonItem {
        return api.request("groups.getLongPollServer", mapOf("group_id" to groupId))
    }

    override fun makeLongRequest(server: Map<String, Any?>): IrisJsonItem {
        return requester.sendJson(BOT_POLLING_URL.format(
                server["server"], server["key"], server["ts"], wait, rpsDelay
        ))
    }

    override fun run() {
        var server = getServer()
        while (!stopped) {
            val event = makeLongRequest(server["response"].asMap())
            if (!event.asMap().containsKey("ts"))
                server = getServer()

            emulate(event)
            server["response"]["ts"] = event["ts"]
        }
    }

    private fun emulate(event: IrisJsonItem) {
        if (!event.asMap().containsKey("updates") || event["updates"].isNull()) // Проверить
            return

    }
}