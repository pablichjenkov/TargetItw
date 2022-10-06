package com.target.targetcasestudy.ui.view.adapter.renders

import com.target.targetcasestudy.ui.view.adapter.DealInfoCell
import com.target.targetcasestudy.ui.view.adapter.viewholder.InfoCellViewHolder
import com.target.targetcasestudy.ui.view.adapter.viewholder.GenericViewHolder

class InfoCellRender(
    cell: DealInfoCell
) : CellRender<DealInfoCell>(cell) {
    override fun bind(holder: GenericViewHolder) {
        val infoCellHolder = holder as InfoCellViewHolder
        infoCellHolder.itemDescription.text = cell.deal.description
    }

}