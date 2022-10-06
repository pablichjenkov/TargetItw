package com.target.targetcasestudy.ui.view.adapter.renders

import com.target.targetcasestudy.ui.view.adapter.DealCellSmall
import com.target.targetcasestudy.ui.view.adapter.viewholder.GenericViewHolder
import com.target.targetcasestudy.ui.view.adapter.viewholder.SmallCellViewHolder

class SmallCellRender(
    cell: DealCellSmall,
    val onClick: (cell: DealCellSmall) -> Unit
) : CellRender<DealCellSmall>(cell) {

    override fun bind(holder: GenericViewHolder) {
        val smallCellHolder = holder as SmallCellViewHolder
        bindInternal(cell, smallCellHolder)
    }

    private fun bindInternal(cell: DealCellSmall, holder: SmallCellViewHolder) {
        holder.itemView.setOnClickListener {
            onClick(cell)
        }
        holder.currentCell = cell
        loadImage(holder.itemImage, cell.deal.imageUrl)
        holder.itemTitle.text = cell.deal.title
        holder.itemPrice.text = cell.deal.salePrice.displayString
        holder.itemRegPrice.text = cell.deal.salePrice.displayString
        holder.itemOnlineStatus.text = cell.deal.fulfillment
        holder.itemAvailability.text = cell.deal.availability
        holder.aisleInfo.text = cell.deal.aisle
    }

}

