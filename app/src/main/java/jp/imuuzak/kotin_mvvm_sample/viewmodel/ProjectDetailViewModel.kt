package jp.imuuzak.kotin_mvvm_sample.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import jp.imuuzak.kotin_mvvm_sample.R
import jp.imuuzak.kotin_mvvm_sample.service.model.Project
import jp.imuuzak.kotin_mvvm_sample.service.repository.ProjectRepository
import kotlinx.coroutines.launch

class ProjectDetailViewModel(
    private val app: Application,
    private val projectId: String
) : AndroidViewModel(app) {
    private val repository = ProjectRepository.instance
    val projectLiveData: MutableLiveData<Project> = MutableLiveData()
    var project = ObservableField<Project>()

    init {
        loadProject()
    }

    private fun loadProject() {
        viewModelScope.launch {
            try {
                val result = repository.getProjectDetail(
                    app.getString(R.string.github_user_name),
                    projectId
                )
                if (result.isSuccessful) {
                    projectLiveData.postValue(result.body())
                }
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }

    fun setProject(project: Project) {
        this.project.set(project)
    }

    class Factory(private val app: Application, private val projectId: String) :
        ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProjectDetailViewModel(app, projectId) as T
        }
    }
}