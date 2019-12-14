package jp.imuuzak.kotin_mvvm_sample.view.callback

import jp.imuuzak.kotin_mvvm_sample.service.model.Project

interface ProjectClickCallback {
    fun onClick(project: Project)
}