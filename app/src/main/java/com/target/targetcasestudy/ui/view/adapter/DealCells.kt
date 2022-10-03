package com.target.targetcasestudy.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.target.targetcasestudy.R
import com.target.targetcasestudy.api.DealFull
import com.target.targetcasestudy.api.DealPartial
import com.target.targetcasestudy.data.ApiError

/**
 * DealCellSmall
 * */

class DealCellSmall(val deal: DealPartial) : IGenericCell(R.layout.deal_list_item)

class DealItemSmallViewHolder(
    itemView: View,
    onCellClick: (DealCellSmall) -> Unit
) : GenericViewHolder(itemView) {
    val itemImage = itemView.findViewById<ImageView>(R.id.deal_list_item_image_view)
    val itemTitle = itemView.findViewById<TextView>(R.id.deal_list_item_title)
    val itemPrice = itemView.findViewById<TextView>(R.id.deal_list_item_price)
    val itemRegPrice = itemView.findViewById<TextView>(R.id.deal_list_item_price_reg)
    val itemOnlineStatus = itemView.findViewById<TextView>(R.id.deal_list_item_online_status)
    val itemAvailability = itemView.findViewById<TextView>(R.id.deal_list_item_stock_status)
    val aisleInfo = itemView.findViewById<TextView>(R.id.deal_list_item_stock_info)

    lateinit var currentCell: DealCellSmall

    init {
        itemView.setOnClickListener {
            onCellClick(currentCell)
        }
    }

}

class SmallCellRenderer : BaseRenderer<DealCellSmall, DealItemSmallViewHolder>() {
    override fun bind(cell: DealCellSmall, holder: DealItemSmallViewHolder) {
        holder.currentCell = cell
        loadImage(holder.itemImage, cell.deal.imageUrl)
        holder.itemTitle.text = cell.deal.title
        holder.itemPrice.text = cell.deal.salePrice.displayString
        holder.itemRegPrice.text = cell.deal.salePrice.displayString
        holder.itemOnlineStatus.text = cell.deal.fulfillment
        holder.itemAvailability.text = cell.deal.availability
        holder.aisleInfo.text = cell.deal.aisle
    }

    override fun getVH(
        parent: ViewGroup,
        onCellClick: (DealCellSmall) -> Unit
    ): DealItemSmallViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.deal_list_item, parent, false)
        return DealItemSmallViewHolder(view, onCellClick)
    }
}

/**
 * DealCellDescription
 * */

class DealCellDescription(val deal: DealFull) : IGenericCell(R.layout.deal_list_item_description)

class DealItemDescViewHolder(
    itemView: View,
) : GenericViewHolder(itemView) {
    val itemDescription = itemView.findViewById<TextView>(R.id.deal_list_item_description)
}

class DescriptionCellRenderer : BaseRenderer<DealCellDescription, DealItemDescViewHolder>() {
    override fun bind(cell: DealCellDescription, holder: DealItemDescViewHolder) {
        holder.itemDescription.text = cell.deal.description
    }

    override fun getVH(
        parent: ViewGroup,
        onCellClick: (DealCellSmall) -> Unit
    ): DealItemDescViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.deal_list_item_description, parent, false)
        return DealItemDescViewHolder(view)
    }
}

/**
 * DealCellBig
 * */

class DealCellBig(val deal: DealFull) : IGenericCell(R.layout.deal_list_item_big)

class DealItemBigViewHolder(
    itemView: View,
) : GenericViewHolder(itemView) {
    val itemImage = itemView.findViewById<ImageView>(R.id.deal_list_item_image_view)
    val itemTitle = itemView.findViewById<TextView>(R.id.deal_list_item_title)
    val itemPrice = itemView.findViewById<TextView>(R.id.deal_list_item_price)
    val itemRegPrice = itemView.findViewById<TextView>(R.id.deal_list_item_price_reg)
    val itemOnlineStatus = itemView.findViewById<TextView>(R.id.deal_list_item_online_status)
}

class BigCellRenderer : BaseRenderer<DealCellBig, DealItemBigViewHolder>() {
    override fun bind(cell: DealCellBig, holder: DealItemBigViewHolder) {
        loadImage(holder.itemImage, cell.deal.imageUrl)
        holder.itemTitle.text = cell.deal.title
        holder.itemPrice.text = cell.deal.salePrice.displayString
        holder.itemRegPrice.text = cell.deal.salePrice.displayString
        holder.itemOnlineStatus.text = cell.deal.fulfillment
    }

    override fun getVH(
        parent: ViewGroup,
        onCellClick: (DealCellSmall) -> Unit
    ): DealItemBigViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.deal_list_item_big, parent, false)
        return DealItemBigViewHolder(view)
    }
}

/**
 * DealCellDescription
 * */

class ErrorCell(val apiError: ApiError) : IGenericCell(R.layout.deal_list_item_error)

class ErrorCellViewHolder(
    itemView: View,
) : GenericViewHolder(itemView) {
    val itemErrorCode = itemView.findViewById<TextView>(R.id.deal_list_item_error_code)
    val itemErrorMesg = itemView.findViewById<TextView>(R.id.deal_list_item_error_message)
}

class ErrorCellRenderer : BaseRenderer<ErrorCell, ErrorCellViewHolder>() {
    override fun bind(cell: ErrorCell, holder: ErrorCellViewHolder) {
        holder.itemErrorCode.text = cell.apiError.code
        holder.itemErrorMesg.text = cell.apiError.message
    }

    override fun getVH(
        parent: ViewGroup,
        onCellClick: (DealCellSmall) -> Unit
    ): ErrorCellViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.deal_list_item_error, parent, false)
        return ErrorCellViewHolder(view)
    }
}