package com.entreprisecorp.zikfrip.payments

import android.os.Build
import androidx.annotation.RequiresApi
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.entreprisecorp.zikfrip.MainActivity
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.HashMap

class Payments {

    val clientID = "tomkubasik"
    val apikey = "ZfcfrSHttTKVtAy7jM4gynihPBRV0DSQ79F3aydd14fqWM5MFD"
    val serverURL = "https://api.sandbox.mangopay.com"
    val versionMango = "V2.01"


    var userID = ""

    fun createNaturalClient(name: String, firstname : String, activity: MainActivity){
        val jsonParams = JSONObject()
                .put("FirstName", firstname)
                .put("LastName", name)
                .put("Birthday", 1463496101)
                .put("Nationality", "FR")
                .put("CountryOfResidence", "FR")
                .put("Email", "test@gmail.com")


        val queue = Volley.newRequestQueue(activity)

        val url = "https://api.sandbox.mangopay.com/v2.01/$clientID/users/natural"
        val request = object : JsonObjectRequest(Request.Method.POST, url, jsonParams, Response.Listener { response ->
            try {
                userID = response.getString("Id")
                println("userId " + userID)

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
}