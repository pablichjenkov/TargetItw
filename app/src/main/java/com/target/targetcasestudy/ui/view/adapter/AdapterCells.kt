package com.target.targetcasestudy.ui.view.adapter

import androidx.annotation.LayoutRes
import com.target.targetcasestudy.R
import com.target.targetcasestudy.api.DealFull
import com.target.targetcasestudy.api.DealPartial
import com.target.targetcasestudy.data.ApiError

sealed class IGenericCell(@LayoutRes val viewType: Int)

class DealSmallCell(val deal: DealPartial) : IGenericCell(R.layout.deal_list_item)

class DealInfoCell(val deal: DealFull) : IGenericCell(R.layout.deal_list_item_description)

class BigDealCell(val deal: DealFull) : IGenericCell(R.layout.deal_list_item_big)

class ErrorCell(val apiError: ApiError) : IGenericCell(R.layout.deal_list_item_error)
