package jp.imuuzak.kotin_mvvm_sample.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.imuuzak.kotin_mvvm_sample.R
import jp.imuuzak.kotin_mvvm_sample.service.model.Project

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val projectListFragment = ProjectListFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, projectListFragment, ProjectListFragment.TAG)
                .commit()
        }
    }

    fun showProject(project: Project) {
        val projectDetailFragment = ProjectDetailFragment.forProject(project.name)
        supportFragmentManager
            .beginTransaction()
            .addToBackStack("project")
            .replace(R.id.fragment_container, projectDetailFragment, null)
            .commit()
    }
}
