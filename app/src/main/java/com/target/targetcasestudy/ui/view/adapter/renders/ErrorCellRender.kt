package com.target.targetcasestudy.ui.view.adapter.renders

import com.target.targetcasestudy.ui.view.adapter.ErrorCell
import com.target.targetcasestudy.ui.view.adapter.viewholder.ErrorCellViewHolder
import com.target.targetcasestudy.ui.view.adapter.viewholder.GenericViewHolder

class ErrorCellRender(
    cell: ErrorCell
) : CellRender<ErrorCell>(cell) {
    override fun bind(holder: GenericViewHolder) {
        val errorHolder = holder as ErrorCellViewHolder
        errorHolder.itemErrorCode.text = cell.apiError.code
        errorHolder.itemErrorMesg.text = cell.apiError.message
    }
}