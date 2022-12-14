package com.target.targetcasestudy.ui.view.adapter.renders

import com.target.targetcasestudy.ui.view.adapter.BigDealCell
import com.target.targetcasestudy.ui.view.adapter.viewholder.BigCellViewHolder
import com.target.targetcasestudy.ui.view.adapter.viewholder.GenericViewHolder

class BigCellRender(
    cell: BigDealCell
) : CellRender<BigDealCell>(cell) {
    override fun bind(holder: GenericViewHolder) {
        val bigCellHolder = holder as BigCellViewHolder
        loadImage(holder.itemImage, cell.deal.imageUrl)
        bigCellHolder.itemTitle.text = cell.deal.title
        bigCellHolder.itemPrice.text = cell.deal.salePrice.displayString
        bigCellHolder.itemRegPrice.text = cell.deal.salePrice.displayString
        bigCellHolder.itemOnlineStatus.text = cell.deal.fulfillment
    }
}