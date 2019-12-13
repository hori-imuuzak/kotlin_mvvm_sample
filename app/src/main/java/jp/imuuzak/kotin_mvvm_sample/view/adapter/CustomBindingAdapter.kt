package jp.imuuzak.kotin_mvvm_sample.view.adapter

import android.view.View
import androidx.databinding.BindingAdapter

object CustomBindingAdapter {
    @BindingAdapter("visibleGone")
    @JvmStatic
    fun setVisibleGone(view: View, isVisibleGone: Boolean) {
        view.visibility = if (isVisibleGone) View.GONE else View.VISIBLE
    }
}