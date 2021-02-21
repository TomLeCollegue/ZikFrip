package com.entreprisecorp.zikfrip

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.entreprisecorp.zikfrip.storage.ProductStorage.Singleton.productList
import com.entreprisecorp.zikfrip.adapters.ProductAdapter
import com.entreprisecorp.zikfrip.storage.Product
import com.entreprisecorp.zikfrip.storage.ProductStorage

class CategoryFragment : Fragment() {

    var category = ""
    val ListProductCat = ArrayList<Product>()

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = arguments!!.getString("category")!!

        //ListProductCat = category?.let { ProductStorage.getProductbyCategoy(it) }!!

        activity?.findViewById<TextView>(R.id.textSubtitle)?.text = category

    }
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
        val adapter = ProductAdapter(R.layout.item_product_layout, ListProductCat, activity)
        recyclerViewProduct.adapter = adapter
        ProductStorage.getProductbyCategoy(category,ListProductCat,adapter,activity as MainActivity)
    }

}