package com.target.targetcasestudy.ui.view.adapter.renders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.view.adapter.*
import com.target.targetcasestudy.ui.view.adapter.viewholder.*

abstract class CellRender<C : IGenericCell>(val cell: C) {

    abstract fun bind(holder: GenericViewHolder)

    companion object {
        fun getViewHolderForViewType(
            viewType: Int,
            parent: ViewGroup
        ): GenericViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return when(viewType) {
                R.layout.deal_list_item -> {
                    val view = inflater.inflate(R.layout.deal_list_item, parent, false)
                    SmallCellViewHolder(view, {})
                }
                R.layout.deal_list_item_big -> {
                    val view = inflater.inflate(R.layout.deal_list_item_big, parent, false)
                    BigCellViewHolder(view)
                }
                R.layout.deal_list_item_description -> {
                    val view = inflater.inflate(R.layout.deal_list_item_description, parent, false)
                    InfoCellViewHolder(view)
                }
                R.layout.deal_list_item_error -> {
                    val view = inflater.inflate(R.layout.deal_list_item_error, parent, false)
                    ErrorCellViewHolder(view)
                }
                else -> throw IllegalStateException("Missing GenericViewHolder Type")
            }
        }

        fun loadImage(imageView: ImageView, imageUrl: String) {
            val requestOptions =
                RequestOptions().transform(CenterInside(), RoundedCorners(24))

            Glide.with(imageView.context)
                .load(imageUrl)
                .apply(requestOptions)
                .into(imageView)
        }
    }

}