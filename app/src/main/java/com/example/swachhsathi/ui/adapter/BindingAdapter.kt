package com.example.swachhsathi.ui.adapter


import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("app:swipeRefreshState")
fun setSwipeRefreshState(view: SwipeRefreshLayout, isRefreshing: Boolean) {
    if (view.isRefreshing != isRefreshing) {
        view.isRefreshing = isRefreshing
    }
}
