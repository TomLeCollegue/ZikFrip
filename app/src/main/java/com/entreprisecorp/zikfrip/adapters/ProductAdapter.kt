package com.entreprisecorp.zikfrip.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.entreprisecorp.zikfrip.Product
import com.entreprisecorp.zikfrip.R
import kotlinx.android.synthetic.main.item_frontpage_layout.view.*

class ProductAdapter(val layoutID : Int, val productList: List<Product>, val context: Context?) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imageItem = view.findViewById<ImageView>(R.id.imageItem)
        val nameItem:TextView? = view.findViewById(R.id.textNameProduct)
        val descriptionItem:TextView? = view.findViewById(R.id.textDescProduct)
        val priceItem:TextView? = view.findViewById(R.id.textPriceProduct)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(layoutID,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentProduct = productList[position]
        if (context != null) {
            Glide.with(context).load(Uri.parse(currentProduct.imageURL)).into(holder.imageItem)
        }

        holder.nameItem?.text = currentProduct.name
        holder.descriptionItem?.text = currentProduct.description
        holder.priceItem?.text = currentProduct.price.toString() + ",-€"

        holder.imageItem.setOnClickListener {

            val bundle = bundleOf("position" to position)

            if(layoutID == R.layout.item_frontpage_layout){
                Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_productFragment, bundle);
            }
            else if(layoutID == R.layout.item_product_layout){
                Navigation.findNavController(it).navigate(R.id.action_categoryFragment_to_productFragment, bundle);
            }
        }
    }

}