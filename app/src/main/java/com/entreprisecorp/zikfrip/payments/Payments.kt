package com.entreprisecorp.zikfrip.payments

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.entreprisecorp.zikfrip.MainActivity
import com.entreprisecorp.zikfrip.storage.Product
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.HashMap

class Payments {

    val clientID = "tomkubasik"
    val apikey = "ZfcfrSHttTKVtAy7jM4gynihPBRV0DSQ79F3aydd14fqWM5MFD"


    var userID = ""

    fun createNaturalClient(name: String, firstname : String, activity: MainActivity, product: Product){
        val jsonParams = JSONObject()
                .put("FirstName", firstname)
                .put("LastName", name)
                .put("Birthday", 1463496101)
                .put("Nationality", "FR")
                .put("CountryOfResidence", "FR")
                .put("Email", "$firstname$name@gmail.com")


        val queue = Volley.newRequestQueue(activity)

        val url = "https://api.sandbox.mangopay.com/v2.01/$clientID/users/natural"
        val request = object : JsonObjectRequest(Request.Method.POST, url, jsonParams, Response.Listener { response ->
            try {
                userID = response.getString("Id")
                Pay(product,activity)

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, Response.ErrorListener { error -> error.printStackTrace() })
        {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] =  "Basic " + Base64.getEncoder().encodeToString((clientID + ":" + apikey).toByteArray())
                return headers
            }
        }
        queue.add(request)
    }

    fun Pay(product:Product, activity: MainActivity){
        val jsonParams = JSONObject()
                .put("AuthorId", userID)
                .put("DebitedFunds", JSONObject().put("Currency", "EUR").put("Amount", ((product.price + product.deliveryFee) * 100).toInt()))
                .put("Fees", JSONObject().put("Currency", "EUR").put("Amount", 0))
                .put("ReturnURL", "http://89.87.13.28:8800/Fac/SucceedMangoPay.html")
                .put("CardType", "CB_VISA_MASTERCARD")
                .put("CreditedWalletId", "101937634")
                .put("Culture", "FR")

        val queue = Volley.newRequestQueue(activity)
        val url = "https://api.sandbox.mangopay.com/v2.01/$clientID/payins/card/web"

        val request = object : JsonObjectRequest(Request.Method.POST, url, jsonParams, Response.Listener { response ->
            try {
                val redirectURL = response.getString("RedirectURL")
                getUrlFromIntent(activity, redirectURL)

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, Response.ErrorListener { error -> error.printStackTrace() })
        {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] =  "Basic " + Base64.getEncoder().encodeToString((clientID + ":" + apikey).toByteArray())
                return headers
            }
        }
        queue.add(request)


    }

    fun getUrlFromIntent(activity: MainActivity, url : String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        activity.startActivity(intent)
    }



}