package com.entreprisecorp.zikfrip

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.entreprisecorp.zikfrip.ProductStorage.Singleton.productList
import com.entreprisecorp.zikfrip.adapters.CategoriesAdapter
import com.entreprisecorp.zikfrip.adapters.ProductAdapter


class HomeFragment(
) : Fragment() {

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


        productList.add(Product("Stratocaster Rouge", "tres bon etat a vendre", "https://dmldbxcvbvfk2.cloudfront.net/images/1962-fender-stratocaster-fiesta-red-6-1.jpg", 150, "Guitare"))
        productList.add(Product("Cajon Bois", "Percu sympa", "https://www.bax-shop.fr/blog/wp-content/uploads/2019/03/blog_cajon_ritmes.jpg", 100, "Percussion"))
        productList.add(Product("Stratocaster Rouge", "tres bon etat a vendre", "https://dmldbxcvbvfk2.cloudfront.net/images/1962-fender-stratocaster-fiesta-red-6-1.jpg", 150, "Guitare"))
        productList.add(Product("Cajon Bois", "Percu sympa", "https://www.bax-shop.fr/blog/wp-content/uploads/2019/03/blog_cajon_ritmes.jpg", 100, "Percussion"))
        productList.add(Product("Stratocaster Rouge", "tres bon etat a vendre", "https://dmldbxcvbvfk2.cloudfront.net/images/1962-fender-stratocaster-fiesta-red-6-1.jpg", 150, "Guitare"))
        productList.add(Product("Cajon Bois", "Percu sympa", "https://www.bax-shop.fr/blog/wp-content/uploads/2019/03/blog_cajon_ritmes.jpg", 100, "Percussion"))
        productList.add(Product("Stratocaster Rouge", "tres bon etat a vendre", "https://dmldbxcvbvfk2.cloudfront.net/images/1962-fender-stratocaster-fiesta-red-6-1.jpg", 150, "Guitare"))
        productList.add(Product("Cajon Bois", "Percu sympa", "https://www.bax-shop.fr/blog/wp-content/uploads/2019/03/blog_cajon_ritmes.jpg", 100, "Percussion"))


        recyclerViewFront.adapter = ProductAdapter(R.layout.item_frontpage_layout, productList, activity)
        recyclerViewCategories.adapter = CategoriesAdapter()



    }
}