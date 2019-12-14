package jp.imuuzak.kotin_mvvm_sample.service.repository

import jp.imuuzak.kotin_mvvm_sample.service.model.Project
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ProjectRepository {
    private val HTTPS_API_GITHUB_URL = "https://api.github.com/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(HTTPS_API_GITHUB_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    private val githubService: GitHubService = retrofit.create(GitHubService::class.java)

    // 一覧取得コルーチン
    suspend fun getProjectList(userId: String): Response<List<Project>> =
        githubService.getProjectList(userId)

    // 詳細取得コルーチン
    suspend fun getProjetDetail(userId: String, reponame: String): Response<Project> =
        githubService.getProjectDetail(userId, reponame)

    companion object Factory {
        val instance: ProjectRepository
            @Synchronized get() {
                return ProjectRepository()
            }
    }
}