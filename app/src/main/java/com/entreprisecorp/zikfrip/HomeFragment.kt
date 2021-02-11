package com.entreprisecorp.zikfrip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.entreprisecorp.zikfrip.adapters.CategoriesAdapter
import com.entreprisecorp.zikfrip.adapters.ProductAdapter


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerViewFront = view.findViewById<RecyclerView>(R.id.recyclerViewFront)
        val recyclerViewCategories = view.findViewById<RecyclerView>(R.id.recyclerViewCategories)


        recyclerViewFront.adapter = ProductAdapter()
        recyclerViewCategories.adapter = CategoriesAdapter()



    }
}