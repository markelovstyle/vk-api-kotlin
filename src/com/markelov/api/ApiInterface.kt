package com.markelov.api

import iris.json.plain.IrisJsonItem

interface ApiInterface {

    fun request(method: String, params: Map<String, Any>?): IrisJsonItem
}