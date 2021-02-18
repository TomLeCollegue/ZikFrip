
package com.entreprisecorp.zikfrip

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.entreprisecorp.zikfrip.ProductStorage.Singleton.productList
import com.entreprisecorp.zikfrip.adapters.ProductAdapter


class ProductFragment : Fragment() {

    var position: Int = 0
    lateinit var product : Product

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = arguments!!.getInt("position")
        product = productList[position]


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageProduct = view.findViewById<ImageView>(R.id.imageProduct)
        val nameProduct = view.findViewById<TextView>(R.id.textNameProduct)
        val priceProduct = view.findViewById<TextView>(R.id.textPriceProduct)
        val descProduct = view.findViewById<TextView>(R.id.textDescProduct)


        activity?.let {
            Glide.with(it).load(Uri.parse(product.imageURL)).into(imageProduct)
        }
        nameProduct.text = product.name
        priceProduct.text = product.priceToString()
        descProduct.text = product.description


    }

}