
package com.entreprisecorp.zikfrip

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.entreprisecorp.zikfrip.payments.Payments
import com.entreprisecorp.zikfrip.storage.ProductStorage.Singleton.productList
import com.entreprisecorp.zikfrip.storage.Product
import com.entreprisecorp.zikfrip.storage.ProductStorage
import com.entreprisecorp.zikfrip.storage.SessionStorage
import com.entreprisecorp.zikfrip.storage.SessionStorage.Singleton.firstnameSession
import com.entreprisecorp.zikfrip.storage.SessionStorage.Singleton.nameSession
import kotlinx.android.synthetic.main.fragment_product.*
import org.json.JSONException
import org.json.JSONObject


class ProductFragment : Fragment() {

    lateinit var product : Product
    var deliveryFeePrice = 0.0

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        product = arguments!!.getParcelable("product")!!
        activity?.findViewById<TextView>(R.id.textSubtitle)?.text = "Article"
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
        val deliveryFeeText = view.findViewById<TextView>(R.id.textDeliveryFee)
        val payments = Payments()

        //On calcule les frais de port
        ProductStorage.CalculateDeliferyFee(product, activity as MainActivity,deliveryFeeText)
        //On recupere le nom et le prenom
        SessionStorage.getInfoSession(activity as MainActivity)


        activity?.let {
            Glide.with(it).load(Uri.parse(product.imageURL)).into(imageProduct)
        }
        nameProduct.text = product.name
        priceProduct.text = product.priceToString()
        descProduct.text = product.description

        buttonBuy.setOnClickListener {
            payments.createNaturalClient(nameSession, firstnameSession, activity as MainActivity, product)

        }


    }




}