package com.tae.baselibrary.api

class NetworkConst {
    companion object {
        const val USER_AGENT = "User-Agent"
        const val X_API_USER = "X-Api-User"
        const val X_API_NONCE = "X-Api-Nonce"
        const val X_API_TOKEN = "X-Api-Token"
        const val X_API_KEY = "X-Api-Key"

        const val CONTENT_TYPE = "Content-Type"

        var applicationJson = "application/json"
        var userAgent = ""
        var xApiUser = ""
        var xApiNonce = ""
        var xApiToken = ""
        var xApiKey = ""

        var SERVER_IP = ""
        var PORT = ""

        var HTTP_URL = "http://$SERVER_IP:$PORT/"
    }
}