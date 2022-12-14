package com.target.targetcasestudy.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.ViewModelFactory
import com.target.targetcasestudy.ui.view.adapter.*
import com.target.targetcasestudy.ui.view.adapter.renders.BigCellRender
import com.target.targetcasestudy.ui.view.adapter.renders.ErrorCellRender
import com.target.targetcasestudy.ui.view.adapter.renders.InfoCellRender
import com.target.targetcasestudy.ui.viewmodel.DealItemVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DealItemFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var recyclerView: RecyclerView

    private val dealItemVM by viewModels<DealItemVM>(
        factoryProducer = { viewModelFactory }
    )

    private val args: DealItemFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("DealItemFragment", "Pablo dealItemFragment instance = ${this@DealItemFragment}")
        Log.d("DealItemFragment", "Pablo dealItemVM instance = ${dealItemVM}")

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                renderView(this)
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_deal_item, container, false).also {
            recyclerView = it.findViewById(R.id.recycler_view)
            val layoutManager = LinearLayoutManager(requireContext())
            recyclerView.layoutManager = layoutManager
            recyclerView.addItemDecoration(
                VerticalSpaceItemDecoration(24)
            )
        }
    }

    private fun renderView(coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            dealItemVM.fullDealStateFlow.collect {
                when (val fullDealState = it) {
                    is DealItemVM.SingleDealState.Data -> {
                        val fullDealCell = BigDealCell(fullDealState.fullDeal)
                        val descCell = DealInfoCell(fullDealState.fullDeal)

                        recyclerView.adapter = GenericCellAdapter(
                            listOf(
                                BigCellRender(
                                    fullDealCell
                                ),
                                InfoCellRender(
                                    descCell
                                )
                            )
                        )

                    }
                    is DealItemVM.SingleDealState.Error -> {
                        recyclerView.adapter = GenericCellAdapter(
                            listOf(
                                ErrorCellRender(
                                    ErrorCell(fullDealState.apiError)
                                )
                            )
                        )
                    }
                    DealItemVM.SingleDealState.Idle -> {
                        dealItemVM.requestFullDeal(args.dealId)
                    }
                    DealItemVM.SingleDealState.Loading -> {}
                }
            }
        }
    }

}