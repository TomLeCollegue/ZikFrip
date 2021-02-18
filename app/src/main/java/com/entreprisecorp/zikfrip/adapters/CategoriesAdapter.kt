package com.entreprisecorp.zikfrip.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.entreprisecorp.zikfrip.R

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imageItem = view.findViewById<ImageView>(R.id.imageCategory)
        val textCategory = view.findViewById<TextView>(R.id.textCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_categories_layout,parent,false)
        return CategoriesAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.imageItem.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_categoryFragment);
        }

    }


}