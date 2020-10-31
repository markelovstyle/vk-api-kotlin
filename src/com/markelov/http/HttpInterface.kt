package com.markelov.http

import iris.json.plain.IrisJsonItem
import java.net.http.HttpResponse

interface HttpInterface {

    fun send(url: String): HttpResponse<String>
    fun sendJson(url: String): IrisJsonItem
}