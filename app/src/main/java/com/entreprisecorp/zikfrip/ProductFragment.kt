
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
import com.bumptech.glide.Glide
import com.entreprisecorp.zikfrip.storage.ProductStorage.Singleton.productList
import com.entreprisecorp.zikfrip.storage.Product
import com.entreprisecorp.zikfrip.storage.ProductStorage


class ProductFragment : Fragment() {

    lateinit var product : Product
    var deliveryFeePrice = 0

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        product = arguments!!.getParcelable("product")!!
        activity?.findViewById<TextView>(R.id.textSubtitle)?.text = "Article"
        deliveryFeePrice = ProductStorage.CalculateDeliferyFee(product.price)
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
        val deliveryFee = view.findViewById<TextView>(R.id.textDeliveryFee)

        deliveryFee.text = "+ $deliveryFeePrice,-€ de livraison"

        activity?.let {
            Glide.with(it).load(Uri.parse(product.imageURL)).into(imageProduct)
        }
        nameProduct.text = product.name
        priceProduct.text = product.priceToString()
        descProduct.text = product.description


    }

}