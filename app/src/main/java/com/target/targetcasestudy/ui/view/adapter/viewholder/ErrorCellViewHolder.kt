package com.target.targetcasestudy.ui.view.adapter.viewholder

import android.view.View
import android.widget.TextView
import com.target.targetcasestudy.R

class ErrorCellViewHolder(
    itemView: View,
) : GenericViewHolder(itemView) {
    val itemErrorCode = itemView.findViewById<TextView>(R.id.deal_list_item_error_code)
    val itemErrorMesg = itemView.findViewById<TextView>(R.id.deal_list_item_error_message)
}