package com.target.targetcasestudy.ui.view.adapter

import android.view.ViewGroup
import androidx.collection.ArrayMap
import androidx.collection.arrayMapOf
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.R

class GenericCellAdapter(
    private val renderersMap: ArrayMap<Int, BaseRenderer<out IGenericCell, out GenericViewHolder>>,
    private val cellList: List<IGenericCell>,
    private val onCellClick: (DealCellSmall) -> Unit
) : RecyclerView.Adapter<GenericViewHolder>() {

    override fun getItemCount(): Int {
        return cellList.size
    }

    override fun getItemViewType(position: Int): Int {
        return cellList[position].viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        return getViewHolderForViewType(viewType, parent, onCellClick)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        val cell = cellList[position]
        val renderer = getRendererForViewType(cell.viewType)
        renderer.bindIn(cell, holder)
    }

    private fun getRendererForViewType(
        viewType: Int
    ): BaseRenderer<out IGenericCell, out GenericViewHolder> {
        return renderersMap[viewType] ?: throw IllegalStateException("Missing BaseRenderer Type")
    }

    private fun getViewHolderForViewType(
        viewType: Int,
        parent: ViewGroup,
        onCellClick: (DealCellSmall) -> Unit
    ): GenericViewHolder {
        return renderersMap[viewType]?.getVH(parent, onCellClick)
            ?: throw IllegalStateException("Missing GenericViewHolder Type")
    }

}