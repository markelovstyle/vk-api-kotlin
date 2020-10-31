package com.markelov.http

import iris.json.plain.IrisJsonItem
import iris.json.plain.IrisJsonParser
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class HTTPRequest(client: HttpClient? = null): HttpInterface {
    private val client = client ?: HttpClient.newBuilder().build()

    private fun buildRequest(url: String): HttpRequest {
        return HttpRequest.newBuilder().uri(URI.create(url)).build()
    }

    override fun send(url: String): HttpResponse<String> {
        return client.send(buildRequest(url), HttpResponse.BodyHandlers.ofString())
    }

    override fun sendJson(url: String): IrisJsonItem {
        val response = send(url)
        return IrisJsonParser(response.body()).parse()
    }
}