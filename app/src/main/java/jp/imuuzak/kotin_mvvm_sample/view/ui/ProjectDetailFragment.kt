package jp.imuuzak.kotin_mvvm_sample.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import jp.imuuzak.kotin_mvvm_sample.R
import jp.imuuzak.kotin_mvvm_sample.databinding.FragmentProjectDetailBinding
import jp.imuuzak.kotin_mvvm_sample.viewmodel.ProjectDetailViewModel

class ProjectDetailFragment : Fragment() {
    lateinit var binding: FragmentProjectDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_project_detail, container, false)

        return this.binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val projectId = arguments?.getString(PROJECT_ID_KEY)

        val factory = ProjectDetailViewModel.Factory(
            requireActivity().application,
            projectId ?: ""
        )

        val viewModel = ViewModelProviders.of(this, factory).get(ProjectDetailViewModel::class.java)
        binding.apply {
            projectViewModel = viewModel
            isLoading = true
        }

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ProjectDetailViewModel) {
        viewModel.projectLiveData.observe(viewLifecycleOwner, Observer { project ->
            if (project != null) {
                viewModel.setProject(project)
                binding.isLoading = false
            }
        })
    }

    companion object {
        val PROJECT_ID_KEY = "project_id"

        fun forProject(projectId: String): ProjectDetailFragment {
            val fragment = ProjectDetailFragment()
            val args = Bundle().apply {
                putString(PROJECT_ID_KEY, projectId)
            }

            fragment.arguments = args

            return fragment
        }
    }
}