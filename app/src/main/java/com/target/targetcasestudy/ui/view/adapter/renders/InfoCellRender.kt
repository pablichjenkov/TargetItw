package com.target.targetcasestudy.ui.view.adapter.renders

import com.target.targetcasestudy.ui.view.adapter.DealCellDescription
import com.target.targetcasestudy.ui.view.adapter.viewholder.InfoCellViewHolder
import com.target.targetcasestudy.ui.view.adapter.viewholder.GenericViewHolder

class InfoCellRender(
    cell: DealCellDescription
) : CellRender<DealCellDescription>(cell) {
    override fun bind(holder: GenericViewHolder) {
        val infoCellHolder = holder as InfoCellViewHolder
        infoCellHolder.itemDescription.text = cell.deal.description
    }

}