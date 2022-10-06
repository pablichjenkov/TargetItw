package com.target.targetcasestudy.ui.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.ui.view.adapter.renders.CellRender
import com.target.targetcasestudy.ui.view.adapter.viewholder.GenericViewHolder

class GenericCellAdapter(
    //private val renderersMap: ArrayMap<Int, KClass<out BaseRenderer<out IGenericCell, out GenericViewHolder>>>,
    private val cellRenders: List<CellRender<out IGenericCell>>
) : RecyclerView.Adapter<GenericViewHolder>() {

    override fun getItemCount(): Int {
        return cellRenders.size
    }

    override fun getItemViewType(position: Int): Int {
        return cellRenders[position].cell.viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        return getViewHolderForViewType(viewType, parent)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        val cellRender = cellRenders[position]
        cellRender.bind(holder)
        //val renderer = getRendererForViewType(cell.viewType)
        //renderer.bindIn(cell, holder)
    }

    private fun getViewHolderForViewType(
        viewType: Int,
        parent: ViewGroup
    ): GenericViewHolder {
        return CellRender.getViewHolderForViewType(viewType, parent)
    }

}