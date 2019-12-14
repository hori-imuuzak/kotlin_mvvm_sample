package jp.imuuzak.kotin_mvvm_sample.service.repository

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import jp.imuuzak.kotin_mvvm_sample.service.model.Project
import jp.imuuzak.kotin_mvvm_sample.view.adapter.DateJsonAdapter
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

class ProjectRepository {
    private val HTTPS_API_GITHUB_URL = "https://api.github.com/"
    private val githubService: GitHubService

    init {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, DateJsonAdapter())
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(HTTPS_API_GITHUB_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        githubService = retrofit.create(GitHubService::class.java)
    }

    // 一覧取得コルーチン
    suspend fun getProjectList(userId: String): Response<List<Project>> =
        githubService.getProjectList(userId)

    // 詳細取得コルーチン
    suspend fun getProjectDetail(userId: String, reponame: String): Response<Project> =
        githubService.getProjectDetail(userId, reponame)

    companion object Factory {
        val instance: ProjectRepository
            @Synchronized get() {
                return ProjectRepository()
            }
    }
}