package com.example.userlist.bindingadapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.userlist.R


@BindingAdapter("imgeURL")
fun loadImageUrl(view: View, url: String?){
    if (!url.isNullOrEmpty() && (view is ImageView)) {
        Glide.with(view.context)
            .load(url)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.spinner_loading)
                    .error(R.drawable.img_empty_imgage)
            )
            .into(view)
        view.rootView.requestLayout()
    }
}

@BindingAdapter("viewVisibility")
fun viewVisibility(view: View, isVisible: Boolean ){
   if(isVisible) view.visibility = View.VISIBLE
    else view.visibility = View.GONE
}

