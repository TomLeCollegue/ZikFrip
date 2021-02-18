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
import com.entreprisecorp.zikfrip.storage.Category
import com.entreprisecorp.zikfrip.R

class CategoriesAdapter(val categoriesList: List<Category>, val context: Context?) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imageItem = view.findViewById<ImageView>(R.id.imageCategory)
        val textCategory = view.findViewById<TextView>(R.id.textCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_categories_layout,parent,false)
        return CategoriesAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int = categoriesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val currentCategory = categoriesList[position]
        if (context != null) {
            Glide.with(context).load(Uri.parse(currentCategory.urlImage)).into(holder.imageItem)
        }

        holder.textCategory.text = currentCategory.name

        holder.imageItem.setOnClickListener {
            val bundle = bundleOf("category" to currentCategory.name)
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_categoryFragment, bundle);
        }

    }


}