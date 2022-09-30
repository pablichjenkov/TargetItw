package com.target.targetcasestudy.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.DealItem

class DealItemAdapter(
    private val dealItemList: List<DealItem>
) : RecyclerView.Adapter<DealItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.deal_list_item, parent, false)
        return DealItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dealItemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
        val item = dealItemList[position]
        loadImage(viewHolder.itemImage, item.imageUrl)
        viewHolder.itemTitle.text = item.description
        viewHolder.itemPrice.text = item.price
    }

    private fun loadImage(imageView: ImageView, imageUrl: String) {
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterInside(), RoundedCorners(24))
        /*Glide.with(imageView.context)
            .load(imageUrl)
            .apply(requestOptions)
            .skipMemoryCache(true)//for caching the image url in case phone is offline
            .into(imageView)*/

        Glide.with(imageView.context)
            .load(imageUrl)
            .apply(requestOptions)
            .into(imageView)
    }

}

class DealItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val itemImage = itemView.findViewById<ImageView>(R.id.deal_list_item_image_view)
    val itemTitle = itemView.findViewById<TextView>(R.id.deal_list_item_title)
    val itemPrice = itemView.findViewById<TextView>(R.id.deal_list_item_price)
}