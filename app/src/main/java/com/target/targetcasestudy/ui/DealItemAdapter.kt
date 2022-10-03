package com.target.targetcasestudy.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.DealCell

class DealItemAdapter(
    private val dealItemList: List<DealCell>
) : RecyclerView.Adapter<DealItemViewHolder>() {

    override fun getItemCount(): Int {
        return dealItemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return dealItemList[position].viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.deal_list_item, parent, false)
        return DealItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
        viewHolder.bind(dealItemList[position])
    }

}

class DealItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val itemImage = itemView.findViewById<ImageView>(R.id.deal_list_item_image_view)
    private val itemTitle = itemView.findViewById<TextView>(R.id.deal_list_item_title)
    private val itemPrice = itemView.findViewById<TextView>(R.id.deal_list_item_price)
    private val itemRegPrice = itemView.findViewById<TextView>(R.id.deal_list_item_price_reg)
    private val itemOnlineStatus = itemView.findViewById<TextView>(R.id.deal_list_item_online_status)
    private val itemAvailability = itemView.findViewById<TextView>(R.id.deal_list_item_stock_status)
    private val aisleInfo = itemView.findViewById<TextView>(R.id.deal_list_item_stock_info)

    fun bind(dealCell: DealCell) {
        loadImage(itemImage, dealCell.deal.imageUrl)
        itemTitle.text = dealCell.deal.description
        itemPrice.text = dealCell.deal.salePrice.displayString
        itemRegPrice.text = dealCell.deal.salePrice.displayString
        itemOnlineStatus.text = dealCell.deal.fulfillment
        itemAvailability.text = dealCell.deal.availability
        aisleInfo.text = dealCell.deal.aisle
    }

    private fun loadImage(imageView: ImageView, imageUrl: String) {
        val requestOptions =
            RequestOptions().transform(CenterInside(), RoundedCorners(24))

        Glide.with(imageView.context)
            .load(imageUrl)
            .apply(requestOptions)
            .into(imageView)
    }

}