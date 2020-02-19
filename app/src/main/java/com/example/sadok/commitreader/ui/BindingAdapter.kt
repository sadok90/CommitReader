package com.example.sadok.commitreader.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sadok.commitreader.R
import kotlinx.android.synthetic.main.commit_item.view.*
import org.w3c.dom.Entity

/**
 * Binding adapter used to hide the spinner once data is available
 */
@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, it: Any?) {
    view.visibility = if (it != null) View.GONE else View.VISIBLE
}

/**
 * Binding adapter used to show empty image once no data available
 */
@BindingAdapter("visibleIfNullOrEmpty")
fun visibleIfNullOrEmpty(view: View, it: List<Any>?) {
    view.visibility = if (it.isNullOrEmpty()) View.VISIBLE else View.GONE
}

/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    if(!url.isNullOrBlank())
        Glide.with(imageView.context).load(url).apply(RequestOptions.circleCropTransform()).into(imageView)

    else {
        imageView.setImageResource(R.drawable.ic_user)
    }
}