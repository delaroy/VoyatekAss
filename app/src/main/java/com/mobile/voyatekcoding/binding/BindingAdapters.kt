package com.mobile.voyatekcoding.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("userValue")
fun bindUserValue(view: TextView, value: String?) {
    value?.let {
        view.text = value
    }
}