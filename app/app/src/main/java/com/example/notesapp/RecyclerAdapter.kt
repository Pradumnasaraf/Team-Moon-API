package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(val notes: List<NotesModal>, mainActivity: MainActivity) :
    RecyclerView.Adapter<RecyclerAdapter.RecyclerViewViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.noteview, parent, false)
        return RecyclerViewViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {

        holder.title.text = notes[position].title
        holder.description.text = notes[position].description

    }

    override fun getItemCount(): Int {
        return notes.size
    }

    inner class RecyclerViewViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val description: TextView = view.findViewById(R.id.tv_description)
        val title: TextView = view.findViewById(R.id.tv_title)

        init {
            view.setOnClickListener {
                val position = adapterPosition
                val title = notes[position].title
                val description = notes[position].description
                val _id = notes[position]._id

                val intent = Intent(view.context, EditActivity::class.java)

                val bundle = Bundle()
                bundle.putString("_id", _id)
                bundle.putString("title", title)
                bundle.putString("description", description)

                intent.putExtras(bundle)

                view.context.startActivity(intent)
            }
        }

    }
}