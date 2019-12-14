package jp.imuuzak.kotin_mvvm_sample.service.repository

import jp.imuuzak.kotin_mvvm_sample.service.model.Project
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    // 一覧
    @GET("users/{user}/repos")
    suspend fun getProjectList(@Path("user") user: String): Response<List<Project>>

    // 詳細
    @GET("repos/{user}/{reponame}")
    suspend fun getProjectDetail(@Path("user") user: String, @Path("reponame") reponame: String): Response<Project>
}