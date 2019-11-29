package com.omerilhanli.newsapimvvm.asistant.util

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseMethod
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omerilhanli.newsapimvvm.R
import com.omerilhanli.newsapimvvm.asistant.callback.ItemClickListener
import com.omerilhanli.newsapimvvm.asistant.extension.dateFormat
import com.omerilhanli.newsapimvvm.mvvm.model.entity.Article
import com.omerilhanli.newsapimvvm.mvvm.view.adapter.ArticleAdapter

object UtilBinding {

    @JvmStatic
    @BindingAdapter(
        "addFragment",
        "fragmentManager",
        requireAll = false
    )
    fun FrameLayout.addFragment(fragment: Fragment, fragmentManager: FragmentManager) {
        fragmentManager
            .beginTransaction()
            .apply {
                replace(R.id.mainFrameContainer, fragment)
            }
            .commit()
    }

    @JvmStatic
    @BindingAdapter(
        "adapter",
        "adapterData",
        "adapterListener",
        "visibility",
        requireAll = false
    )
    fun RecyclerView.prepareAdapter(
        _adapter: ArticleAdapter,
        list: List<Article>?,
        listener: ItemClickListener,
        visible: Boolean
    ) {
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        addItemDecoration(decoration)
        _adapter.listener = listener
        if (visible){
            _adapter.updateAndNotify(list)
        } else {
            _adapter.updateAndNotify(emptyList())
        }
        adapter = _adapter
    }

    @JvmStatic
    @BindingAdapter("bindUrl")
    fun ImageView.bindUrl(urlToImage: String? = null) {
        if (!urlToImage.isNullOrEmpty()) {
            Glide.with(context).load(urlToImage).centerCrop().into(this)
        }
    }

    @JvmStatic
    @BindingAdapter("android:visibility")
    fun View.visibility(visible: Boolean) {
        visibility = if (visible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("android:text")
    fun TextView.setText(_text: String) {
        text = _text
    }

    @JvmStatic
    @InverseMethod("toFormattedDate")
    fun String.toFormattedDate(): String? {
        return dateFormat(fromApi = true, forApi = false)
    }
}