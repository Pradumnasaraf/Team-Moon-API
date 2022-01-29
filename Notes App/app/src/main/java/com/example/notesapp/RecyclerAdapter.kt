package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(val notes: List<Notes>, mainActivity: MainActivity) :
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

    inner class RecyclerViewViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val description = view.findViewById<TextView>(R.id.tv_description)
        val title = view.findViewById<TextView>(R.id.tv_title)

        init {
            view.setOnClickListener {
                val position = adapterPosition
                val title = notes[position].title
                val description = notes[position].description
                val _id = notes[position]._id
                val intent = Intent(view.context, EditActivity::class.java)
                val bundle: Bundle = Bundle()
                bundle.putString("_id", _id)
                bundle.putString("title", title)
                bundle.putString("description", description)
//                intent.putExtra("_id",_id)
//                intent.putExtra("title",title)
//                intent.putExtra("description",description)
                intent.putExtras(bundle)
//startActivity(view.context,intent,bundle)
                view.context.startActivity(intent)

            }
        }

    }
}