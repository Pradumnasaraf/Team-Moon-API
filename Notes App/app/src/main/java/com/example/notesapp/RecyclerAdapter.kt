package com.example.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(val title: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerAdapter.RecyclerViewViewHolder>() {
    class RecyclerViewViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        // val textView by lazy { view.findViewById(R.id.tv_title ) }
        val description = view.findViewById<TextView>(R.id.tv_description)
        val title = view.findViewById<TextView>(R.id.tv_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.noteview, parent, false)
        return RecyclerViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        holder.title.text = title[position]
    }

    override fun getItemCount(): Int {
        return title.size
    }
}