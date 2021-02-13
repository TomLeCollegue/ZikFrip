package com.entreprisecorp.zikfrip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.entreprisecorp.zikfrip.ProductStorage.Singleton.productList
import com.entreprisecorp.zikfrip.adapters.ProductAdapter

class CategoryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerViewProduct = view.findViewById<RecyclerView>(R.id.recyclerViewProducts)
        recyclerViewProduct.layoutManager = GridLayoutManager(activity, 2)
        recyclerViewProduct.adapter = ProductAdapter(R.layout.item_product_layout, productList, activity)
    }

}