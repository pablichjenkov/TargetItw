package com.target.targetcasestudy.ui

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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.R
import com.target.targetcasestudy.api.Deal
import com.target.targetcasestudy.data.DealCell
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DealListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val dealListVM by viewModels<DealListVM>(
        factoryProducer = { viewModelFactory }
    )

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("DealListFragment", "Pablo DealListFragment instance = ${this@DealListFragment}")
        Log.d("DealListFragment", "Pablo dealListVM instance = ${dealListVM}")

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
        return inflater.inflate(R.layout.fragment_deal_list, container, false).also {
            recyclerView = it.findViewById(R.id.recycler_view)
            val layoutManager = LinearLayoutManager(requireContext())
            recyclerView.layoutManager = layoutManager
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    layoutManager.orientation
                )
            )
        }
    }

    private fun renderView(coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            dealListVM.dealListStateFlow.collect {
                when (val dealListState = it) {
                    DealListVM.DealListState.Idle -> {
                    }
                    DealListVM.DealListState.Loading -> {

                    }
                    is DealListVM.DealListState.Data -> {
                        val dealCellList = mapDealToDealCell(dealListState.dealList)
                        recyclerView.adapter = DealItemAdapter(dealCellList)
                    }
                    is DealListVM.DealListState.Error -> {

                    }
                }
            }
        }

        dealListVM.requestDealList()

    }

    private fun mapDealToDealCell(dealList: List<Deal>): List<DealCell> {
        return dealList.map { DealCell(it) }
    }

}