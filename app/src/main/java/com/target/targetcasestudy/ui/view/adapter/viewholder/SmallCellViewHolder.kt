package com.target.targetcasestudy.ui.view.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.view.adapter.DealSmallCell

class SmallCellViewHolder(
    itemView: View,
    onCellClick: (DealSmallCell) -> Unit
) : GenericViewHolder(itemView) {
    val itemImage = itemView.findViewById<ImageView>(R.id.deal_list_item_image_view)
    val itemTitle = itemView.findViewById<TextView>(R.id.deal_list_item_title)
    val itemPrice = itemView.findViewById<TextView>(R.id.deal_list_item_price)
    val itemRegPrice = itemView.findViewById<TextView>(R.id.deal_list_item_price_reg)
    val itemOnlineStatus = itemView.findViewById<TextView>(R.id.deal_list_item_online_status)
    val itemAvailability = itemView.findViewById<TextView>(R.id.deal_list_item_stock_status)
    val aisleInfo = itemView.findViewById<TextView>(R.id.deal_list_item_stock_info)

    lateinit var currentCell: DealSmallCell

    init {
        itemView.setOnClickListener {
            onCellClick(currentCell)
        }
    }

}