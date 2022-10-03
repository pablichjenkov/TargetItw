package com.target.targetcasestudy.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.collection.arrayMapOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.R
import com.target.targetcasestudy.api.DealPartial
import com.target.targetcasestudy.ui.ViewModelFactory
import com.target.targetcasestudy.ui.view.adapter.*
import com.target.targetcasestudy.ui.viewmodel.DealListVM
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
                        dealListVM.requestDealList()
                    }
                    DealListVM.DealListState.Loading -> {

                    }
                    is DealListVM.DealListState.Data -> {
                        val renderersMap = arrayMapOf<Int, BaseRenderer<IGenericCell, GenericViewHolder>>(
                            Pair(R.layout.deal_list_item, SmallCellRenderer() as BaseRenderer<IGenericCell, GenericViewHolder>),
                            Pair(R.layout.deal_list_item_big, BigCellRenderer() as BaseRenderer<IGenericCell, GenericViewHolder>),
                            Pair(R.layout.deal_list_item_description, DescriptionCellRenderer() as BaseRenderer<IGenericCell, GenericViewHolder>)
                        )
                        val dealCellList = mapDealToDealCell(dealListState.dealList)
                        recyclerView.adapter = GenericCellAdapter(
                            renderersMap,
                            dealCellList,
                            ::onCellClick
                        )
                    }
                    is DealListVM.DealListState.Error -> {

                    }
                }
            }
        }
    }

    private fun mapDealToDealCell(dealList: List<DealPartial>): List<DealCellSmall> {
        return dealList.map { DealCellSmall(it) }
    }

    private fun onCellClick(dealCell: DealCellSmall) {
        val navAction = DealListFragmentDirections.navactionDealListFragmentToDealItemFragment(
            dealCell.deal.id
        )
        findNavController().navigate(navAction)
    }

}