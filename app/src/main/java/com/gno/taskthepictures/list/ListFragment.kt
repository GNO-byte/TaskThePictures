package com.gno.taskthepictures.list

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gno.taskthepictures.R

class ListFragment : Fragment(R.layout.fragment_list) {

    lateinit var listViewModel: ListViewModel
    private lateinit var fragment_list_recyclerview: RecyclerView
    private lateinit var customRecyclerAdapter: CustomRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        fragment_list_recyclerview =
            view.findViewById(R.id.fragment_list_recyclerview) as RecyclerView

        listViewModel = ViewModelProvider(requireActivity()).get(ListViewModel::class.java)

        initRecyclerVIew()
        listViewModel.getData()

        listViewModel.PictureListiveData.observe(viewLifecycleOwner, Observer {
            customRecyclerAdapter.submitList(it)
        })

    }

    private fun initRecyclerVIew() {


        val scrollListener = object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount =
                    recyclerView.layoutManager?.childCount
                val totalItemCount = recyclerView.layoutManager?.itemCount
                val firstVisibleItems =
                    (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if ((visibleItemCount!! + firstVisibleItems) >= totalItemCount!!) {
                    listViewModel.getData()
                }
            }
        }

        val isTablet = resources.getBoolean(R.bool.isTablet)

        val screenWidth = getWidthScreen()

        customRecyclerAdapter = CustomRecyclerAdapter(isTablet, screenWidth) { url ->
            onCellClickListener(url)
        }

        if (isTablet) fragment_list_recyclerview.layoutManager = GridLayoutManager(activity, 3)
        else fragment_list_recyclerview.layoutManager = GridLayoutManager(activity, 2)

        fragment_list_recyclerview.adapter = customRecyclerAdapter
        fragment_list_recyclerview.addOnScrollListener(scrollListener)

        fragment_list_recyclerview.layoutManager
    }

    private fun getWidthScreen(): Int {
        var displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager
            .defaultDisplay
            .getMetrics(displayMetrics)

        return displayMetrics.widthPixels
    }

    private fun onCellClickListener(url: String) {
        val bundle = bundleOf("url" to url)
        findNavController().navigate(R.id.action_listFragment_to_cardFragment, bundle)
    }

}
