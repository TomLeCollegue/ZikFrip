package com.entreprisecorp.zikfrip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.entreprisecorp.zikfrip.storage.ProductStorage.Singleton.productList
import com.entreprisecorp.zikfrip.adapters.ProductAdapter

class OrdersFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.findViewById<TextView>(R.id.textSubtitle)?.text = getString(R.string.OrdersWheel)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewOrders)
        recyclerView.adapter = ProductAdapter(R.layout.item_bought_layout, productList, activity)
    }
}