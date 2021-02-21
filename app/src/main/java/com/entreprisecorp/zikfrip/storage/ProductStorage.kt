package com.entreprisecorp.zikfrip.storage

import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.entreprisecorp.zikfrip.MainActivity
import com.entreprisecorp.zikfrip.adapters.ProductAdapter
import com.entreprisecorp.zikfrip.storage.ProductStorage.Singleton.productList
import org.json.JSONException
import org.json.JSONObject
import kotlin.math.round

class ProductStorage {

    object Singleton{
        val productList = arrayListOf<Product>(
            Product(
                0,
                "Stratocaster Rouge",
                "tres bon etat a vendre",
                "https://dmldbxcvbvfk2.cloudfront.net/images/1962-fender-stratocaster-fiesta-red-6-1.jpg",
                150,
                "Guitares",
                0.0
            ),
            Product(
                1,
                "Cajon Bois",
                "Percu sympa",
                "https://www.bax-shop.fr/blog/wp-content/uploads/2019/03/blog_cajon_ritmes.jpg",
                100,
                "Percussions",
                0.0
            ),
            Product(
                2,
                "Stratocaster Rouge",
                "tres bon etat a vendre",
                "https://dmldbxcvbvfk2.cloudfront.net/images/1962-fender-stratocaster-fiesta-red-6-1.jpg",
                150,
                "Guitares",
                0.0
            ),
            Product(
                3,
                "Cajon Bois",
                "Percu sympa",
                "https://www.bax-shop.fr/blog/wp-content/uploads/2019/03/blog_cajon_ritmes.jpg",
                100,
                "Percussions",
                0.0
            ),
            Product(
                4,
                "Stratocaster Rouge",
                "tres bon etat a vendre",
                "https://dmldbxcvbvfk2.cloudfront.net/images/1962-fender-stratocaster-fiesta-red-6-1.jpg",
                150,
                "Guitares",
                0.0
            ),
            Product(
                5,
                "Cajon Bois",
                "Percu sympa",
                "https://www.bax-shop.fr/blog/wp-content/uploads/2019/03/blog_cajon_ritmes.jpg",
                100,
                "Percussions",
                0.0
            ),
            Product(
                6,
                "Stratocaster Rouge",
                "tres bon etat a vendre",
                "https://dmldbxcvbvfk2.cloudfront.net/images/1962-fender-stratocaster-fiesta-red-6-1.jpg",
                150,
                "Guitares",
                0.0
            ),
            Product(
                7,
                "Cajon Bois",
                "Percu sympa",
                "https://www.bax-shop.fr/blog/wp-content/uploads/2019/03/blog_cajon_ritmes.jpg",
                100,
                "Percussions",
                0.0
            )


        )
        val categoriesList = arrayListOf<Category>(
            Category(
                "Guitares",
                "https://cdn.pixabay.com/photo/2016/11/18/19/55/guitar-1836655_1280.jpg"
            ),
            Category(
                "Pianos",
                "https://cdn.pixabay.com/photo/2020/06/29/19/26/piano-5353974_1280.jpg"
            ),
            Category(
                "Percussions",
                "https://cdn.pixabay.com/photo/2016/11/19/13/57/drum-set-1839383_1280.jpg"
            )
        )
    }

    companion object{
        fun CalculateDeliferyFee(product : Product, activity: MainActivity, text : TextView){
            var deliveryFee : Double = 0.0

            val jsonParams = JSONObject()
            jsonParams.put("weight", product.price.toString())
            jsonParams.put("distance", "1")
            val queue = Volley.newRequestQueue(activity)

            val url = "http://89.87.13.28:55807/MainREST_war/REST/soap/calculDeliveryFee"
            val request = JsonObjectRequest(Request.Method.POST, url, jsonParams, Response.Listener { response ->
                try {
                    val deliveryFeeString = response.getString("result")
                    deliveryFee = deliveryFeeString.toDouble().round(1)
                    text.text = "+ $deliveryFee € de livraison"
                    product.deliveryFee = deliveryFee
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error -> error.printStackTrace() })
            queue.add(request)
        }



        fun getProductbyCategoy(category : String, listProduct:ArrayList<Product>, adapter: ProductAdapter, activity: MainActivity){


            val jsonParams = JSONObject()
            jsonParams.put("query", "{Category(name:\"$category\"){products{name price description imageURL}}}" )
            val queue = Volley.newRequestQueue(activity)
            val url = "http://89.87.13.28:8413/graphql/"
            val request = JsonObjectRequest(Request.Method.POST, url, jsonParams, Response.Listener { response ->
                try {
                    listProduct.clear()
                    val jsonData = response.getJSONObject("data")
                    val jsonArray = jsonData.getJSONArray("Category")
                    val products = jsonArray.getJSONObject(0)
                    val productsArray = products.getJSONArray("products")
                    val arrayLenght = productsArray.length() -1

                    for (i in 0..arrayLenght) {
                        val productJson = productsArray.getJSONObject(i)
                        val name = productJson.getString("name")
                        val price = productJson.getInt("price")
                        val description = productJson.getString("description")
                        val imageURL = productJson.getString("imageURL")
                        val product = Product(i, name, description, imageURL, price, category, 0.0)
                        listProduct.add(product)
                        adapter.notifyDataSetChanged()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error -> error.printStackTrace() })
            queue.add(request)



        }

        fun getAllProduct(idMax : Int, listProduct:ArrayList<Product>, adapter: ProductAdapter, activity: MainActivity){

            val jsonParams = JSONObject()
            jsonParams.put("query", "{Product{name price description imageURL}}" )
            val queue = Volley.newRequestQueue(activity)
            val url = "http://89.87.13.28:8413/graphql/"
            val request = JsonObjectRequest(Request.Method.POST, url, jsonParams, Response.Listener { response ->
                try {
                    listProduct.clear()
                    val jsonData = response.getJSONObject("data")
                    val productsArray = jsonData.getJSONArray("Product")
                    val arrayLenght = productsArray.length() -1

                    for (i in 0..arrayLenght) {
                        val productJson = productsArray.getJSONObject(i)
                        val name = productJson.getString("name")
                        val price = productJson.getInt("price")
                        val description = productJson.getString("description")
                        val imageURL = productJson.getString("imageURL")
                        val product = Product(i, name, description, imageURL, price, "", 0.0)
                        listProduct.add(product)
                        adapter.notifyDataSetChanged()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error -> error.printStackTrace() })
            queue.add(request)
        }

        fun getFirstsproduct(idMax : Int, listProduct:ArrayList<Product>, adapter: ProductAdapter, activity: MainActivity){
            val jsonParams = JSONObject()
            jsonParams.put("query", "{Product(first:$idMax){name price description imageURL}}" )
            val queue = Volley.newRequestQueue(activity)
            val url = "http://89.87.13.28:8413/graphql/"
            val request = JsonObjectRequest(Request.Method.POST, url, jsonParams, Response.Listener { response ->
                try {
                    listProduct.clear()
                    val jsonData = response.getJSONObject("data")
                    val productsArray = jsonData.getJSONArray("Product")
                    val arrayLenght = productsArray.length() -1

                    for (i in 0..arrayLenght) {
                        val productJson = productsArray.getJSONObject(i)
                        val name = productJson.getString("name")
                        val price = productJson.getInt("price")
                        val description = productJson.getString("description")
                        val imageURL = productJson.getString("imageURL")
                        val product = Product(i, name, description, imageURL, price, "", 0.0)
                        listProduct.add(product)
                        adapter.notifyDataSetChanged()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error -> error.printStackTrace() })
            queue.add(request)
        }

        fun Double.round(decimals: Int): Double {
            var multiplier = 1.0
            repeat(decimals) { multiplier *= 10 }
            return round(this * multiplier) / multiplier
        }


    }
}