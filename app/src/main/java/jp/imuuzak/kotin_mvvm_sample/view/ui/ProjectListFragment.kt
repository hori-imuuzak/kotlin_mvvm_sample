package jp.imuuzak.kotin_mvvm_sample.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import jp.imuuzak.kotin_mvvm_sample.R
import jp.imuuzak.kotin_mvvm_sample.databinding.FragmentProjectDetailBinding
import jp.imuuzak.kotin_mvvm_sample.databinding.FragmentProjectListBinding

class ProjectListFragment : Fragment() {
    lateinit var binding: FragmentProjectListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container, false)

        return this.binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    companion object {
        val TAG = "project_list_fragment"
    }
}