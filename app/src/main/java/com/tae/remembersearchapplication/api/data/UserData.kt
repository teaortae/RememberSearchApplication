package com.tae.remembersearchapplication.api.data

import android.text.TextUtils
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList


data class UserRes(
    @SerializedName("total_count")
    val totalCount: Int? = 0,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean? = false,
    val items: ArrayList<User> = arrayListOf()
) {
    val headerLetterList: List<User>
        get() {
            return getHeaderLetter(items)
        }

    private fun getHeaderLetter(usersList: ArrayList<User>): List<User> {
        val mSectionList: ArrayList<User> = arrayListOf()
        usersList.sortWith { user1, user2 ->
            user1.login[0].uppercase()
                .compareTo(
                    user2.login[0].uppercase()
                )
        }
        var lastHeader: String? = ""
        usersList.forEach {
            val header: String = it.login[0].uppercase()
            if (lastHeader != header) {
                lastHeader = header

                mSectionList.add(it.apply {
                    this.header = header
                    isHeader = true
                })
            } else mSectionList.add(it)
        }

        return mSectionList
    }
}

data class User(
    val login: String = "",
    val id: Int = 0,
    val node_id: String = "",
    val avatar_url: String = "",
    val gravatar_id: String = "",
    val url: String = "",
    val html_url: String = "",
    val followers_url: String = "",
    val following_url: String = "",
    val gists_url: String = "",
    val starred_url: String = "",
    val subscriptions_url: String = "",
    val organizations_url: String = "",
    val repos_url: String = "",
    val events_url: String = "",
    val received_events_url: String = "",
    val type: String = "",
    val site_admin: Boolean = false,
    val score: Double = .0,
    var header: String = "",
    var isHeader: Boolean = false
)