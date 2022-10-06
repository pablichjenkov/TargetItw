package com.target.targetcasestudy.ui.view.adapter

import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

abstract class BaseRenderer<M : IGenericCell, VH : GenericViewHolder> {

    protected abstract fun bind(cell: M, holder: VH)

    fun bindIn(cell: IGenericCell, holder: GenericViewHolder) {
        bind(cell as M, holder as VH)
    }

    abstract fun getVH(
        parent: ViewGroup,
        onCellClick: (DealCellSmall) -> Unit
    ): VH

    protected fun loadImage(imageView: ImageView, imageUrl: String) {
        val requestOptions =
            RequestOptions().transform(CenterInside(), RoundedCorners(24))

        Glide.with(imageView.context)
            .load(imageUrl)
            .apply(requestOptions)
            .into(imageView)
    }

}