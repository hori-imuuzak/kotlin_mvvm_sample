package jp.imuuzak.kotin_mvvm_sample.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import jp.imuuzak.kotin_mvvm_sample.R
import jp.imuuzak.kotin_mvvm_sample.databinding.ProjectListItemBinding
import jp.imuuzak.kotin_mvvm_sample.service.model.Project
import jp.imuuzak.kotin_mvvm_sample.view.callback.ProjectClickCallback

class ProjectListAdapter(val clickCallback: ProjectClickCallback) :
    RecyclerView.Adapter<ProjectListAdapter.Companion.ProjectListViewHolder>() {
    private var projectList: List<Project> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectListViewHolder {
        val binding = DataBindingUtil.inflate<ProjectListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.project_list_item,
            parent,
            false
        )

        binding.apply {
            callback = clickCallback
        }

        return ProjectListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return projectList.count()
    }

    override fun onBindViewHolder(holder: ProjectListViewHolder, position: Int) {
        holder.projectListItemBinding.project = projectList.get(position)
        holder.projectListItemBinding.executePendingBindings()
    }

    fun setProjectList(projectList: List<Project>) {
        this.projectList = projectList
        notifyDataSetChanged()
    }

    companion object {
        class ProjectListViewHolder(
            val projectListItemBinding: ProjectListItemBinding
        ) : RecyclerView.ViewHolder(projectListItemBinding.root)
    }
}