package jp.imuuzak.kotin_mvvm_sample.service.model

import java.util.*

data class User(
    val login: String,
    val id: Long,
    val avatar_url: String,
    val gravatar_id: String,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val starred_url: String,
    val subscriptions_url: String,
    val organizations_url: String,
    val repos_url: String,
    val events_url: String,
    val received_events_url: String,
    val type: String,
    val name: String,
    val blog: String,
    val location: String,
    val email: String,
    val public_repos: Int,
    val public_gists: Int,
    val followers: Int,
    val following: String,
    val created_at: Date,
    val updated_at: Date
)
