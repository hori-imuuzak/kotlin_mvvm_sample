package jp.imuuzak.kotin_mvvm_sample.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import jp.imuuzak.kotin_mvvm_sample.R
import jp.imuuzak.kotin_mvvm_sample.service.model.Project
import jp.imuuzak.kotin_mvvm_sample.service.repository.ProjectRepository
import kotlinx.coroutines.launch

class ProjectListViewModel(app: Application) : AndroidViewModel(app) {
    private val repository = ProjectRepository.instance

    // 監視するLiveData
    var projectListLiveData: MutableLiveData<List<Project>> = MutableLiveData()

    init {
        loadProjectList()
    }

    private fun loadProjectList() {
        viewModelScope.launch {
            try {
                val result =
                    repository.getProjectList(getApplication<Application>().getString(R.string.github_user_name))
                if (result.isSuccessful) {
                    projectListLiveData.postValue(result.body())
                }
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }
}