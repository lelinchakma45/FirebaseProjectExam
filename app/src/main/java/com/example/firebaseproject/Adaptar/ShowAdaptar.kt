package com.example.firebaseproject.Adaptar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseproject.DataModel.ItemData
import com.example.firebaseproject.R

class ShowAdaptar(private val newsList: List<ItemData>) :
    RecyclerView.Adapter<ShowAdaptar.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_data, parent, false
        )
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.nameTextView.text = currentItem.name
        holder.ageTextView.text = currentItem.age.toString()
    }

    override fun getItemCount() = newsList.size

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameShow)
        val ageTextView: TextView = itemView.findViewById(R.id.ageShow)
    }
}