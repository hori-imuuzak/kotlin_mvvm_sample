package jp.imuuzak.kotin_mvvm_sample.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import jp.imuuzak.kotin_mvvm_sample.R
import jp.imuuzak.kotin_mvvm_sample.databinding.FragmentProjectDetailBinding

class ProjectDetailFragment: Fragment() {
    lateinit var binding: FragmentProjectDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_detail, container, false)

        return this.binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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