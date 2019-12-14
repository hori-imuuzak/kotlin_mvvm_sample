package jp.imuuzak.kotin_mvvm_sample.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import jp.imuuzak.kotin_mvvm_sample.R
import jp.imuuzak.kotin_mvvm_sample.databinding.FragmentProjectDetailBinding
import jp.imuuzak.kotin_mvvm_sample.databinding.FragmentProjectListBinding
import jp.imuuzak.kotin_mvvm_sample.service.model.Project
import jp.imuuzak.kotin_mvvm_sample.view.adapter.ProjectListAdapter
import jp.imuuzak.kotin_mvvm_sample.view.callback.ProjectClickCallback
import jp.imuuzak.kotin_mvvm_sample.viewmodel.ProjectListViewModel

class ProjectListFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ProjectListViewModel::class.java)
    }
    private lateinit var binding: FragmentProjectListBinding
    private lateinit var projectListAdapter: ProjectListAdapter

    private val projectClickCallback = object : ProjectClickCallback {
        override fun onClick(project: Project) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) && activity is MainActivity) {
                (activity as MainActivity).showProject(project)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container, false)

        this.projectListAdapter = ProjectListAdapter(projectClickCallback)

        this.binding.apply {
            projectList.adapter = projectListAdapter
            isLoading = true
        }

        return this.binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ProjectListViewModel) {
        viewModel.projectListLiveData.observe(viewLifecycleOwner, Observer { projects ->
            if (projects != null) {
                projectListAdapter.setProjectList(projects)
                binding.isLoading = false
            }
        })
    }

    companion object {
        val TAG = "project_list_fragment"
    }
}