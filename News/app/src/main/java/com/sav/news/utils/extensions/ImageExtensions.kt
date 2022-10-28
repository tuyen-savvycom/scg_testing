package com.sav.news.utils.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("imageUrl")
internal fun ImageView.loadImage(url: String?) {
    Glide.with(this.context).load(url)
        .transition(DrawableTransitionOptions.withCrossFade(200)).dontAnimate().into(this)
}