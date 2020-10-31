package com.markelov.api

import com.markelov.http.HTTPRequest
import com.markelov.utils.API_URL
import com.markelov.utils.API_VERSION
import iris.json.plain.IrisJsonItem
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


class Api(private val token: String, private val version: Double = API_VERSION) : ApiInterface {
    private val requester = HTTPRequest()

    override fun request(method: String, params: Map<String, Any>?): IrisJsonItem {
        val sb = StringBuilder().append(encodeParams(params))
        sb.append("access_token=").append(token).append("&v=").append(version)
        return requester.sendJson(API_URL.format(method, sb.toString()))
    }

    private fun encode(key: String): String {
        return URLEncoder.encode(key, StandardCharsets.UTF_8)
    }

    private fun encodeParams(obj: Map<String, Any>?): String {
        if (obj == null)
            return ""

        val sb = StringBuilder()
        for ((k, v) in obj) {
            sb.append(encode(k)).append('=').append(encode(v.toString())).append("&")
        }
        return sb.toString()
    }

}
