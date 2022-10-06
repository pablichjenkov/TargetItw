package com.target.targetcasestudy.ui.view.adapter.viewholder

import android.view.View
import android.widget.TextView
import com.target.targetcasestudy.R

class InfoCellViewHolder(
    itemView: View,
) : GenericViewHolder(itemView) {
    val itemDescription = itemView.findViewById<TextView>(R.id.deal_list_item_description)
}
