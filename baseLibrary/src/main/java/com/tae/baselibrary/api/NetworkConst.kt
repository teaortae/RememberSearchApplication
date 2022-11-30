package com.tae.baselibrary.api

class NetworkConst {
    companion object {
        const val CONTENT_TYPE = "Content-Type"
        const val AUTH = "Authorization"
        const val ACCEPT = "Accept"

        var applicationJson = "application/json"
        var applicationGJson = "application/vnd.github+json"
        var token = "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"

        var SERVER_IP = ""
        var PORT = ""

        var HTTP_URL = "http://$SERVER_IP:$PORT/"
    }
}