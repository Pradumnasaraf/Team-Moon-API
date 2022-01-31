package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity(), NoteService {
    lateinit var recyclerview: RecyclerView
    lateinit var buttons: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerview = findViewById(R.id.rvNotes)
        // val list = ArrayList<String>(10)
        //// val adapter = RecyclerAdapter(list)

        buttons = findViewById(R.id.button)

        fetchTodos()
        buttons.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddNotesActivity::class.java))
        }
    }

    fun fetchTodos() {
        val service = retrofit.create(NotesAPI::class.java)
        CoroutineScope(Dispatchers.IO).launch {

            // Do the POST request and get response
            val response = service.getToDO()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val json = response.body()
                    if (json != null) {
                        initRecyclerview(json)
                    }
                    Log.d("MainActivity", json.toString())
                }

//
//            } catch (e: IOException) {
//                Log.e("mirror", e.message!!)
//            }
            }

        }
    }

    private fun initRecyclerview(noteList: List<NotesModal>) {
        val adapter = RecyclerAdapter(noteList, this)
        val layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = adapter
    }


    override fun onResume() {

        fetchTodos()
        super.onResume()
    }
}


