package com.example.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(val title: List<Movie>) :
    RecyclerView.Adapter<RecyclerAdapter.RecyclerViewViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.noteview, parent, false)
        return RecyclerViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        holder.title.text = title[position].title
        holder.description.text = title[position].description
    }

    override fun getItemCount(): Int {
        return title.size
    }

    inner class RecyclerViewViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val description = view.findViewById<TextView>(R.id.tv_description)
        val title = view.findViewById<TextView>(R.id.tv_title)

    }
}