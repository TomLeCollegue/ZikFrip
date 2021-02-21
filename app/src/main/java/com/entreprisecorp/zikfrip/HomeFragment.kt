package com.entreprisecorp.zikfrip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.entreprisecorp.zikfrip.storage.ProductStorage.Singleton.categoriesList
import com.entreprisecorp.zikfrip.storage.ProductStorage.Singleton.productList
import com.entreprisecorp.zikfrip.adapters.CategoriesAdapter
import com.entreprisecorp.zikfrip.adapters.ProductAdapter
import com.entreprisecorp.zikfrip.storage.Product
import com.entreprisecorp.zikfrip.storage.ProductStorage


class HomeFragment(
) : Fragment() {

    val ListProduct = ArrayList<Product>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onResume() {
        super.onResume()
        activity?.findViewById<TextView>(R.id.textSubtitle)?.text = "Accueil"
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerViewFront = view.findViewById<RecyclerView>(R.id.recyclerViewFront)
        val recyclerViewCategories = view.findViewById<RecyclerView>(R.id.recyclerViewCategories)


        val adapter = ProductAdapter(R.layout.item_frontpage_layout, ListProduct, activity)
        recyclerViewFront.adapter = adapter
        recyclerViewCategories.adapter = CategoriesAdapter(categoriesList, activity)

        ProductStorage.getFirstsproduct(4,ListProduct,adapter,activity as MainActivity)



    }
}