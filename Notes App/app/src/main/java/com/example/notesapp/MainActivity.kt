package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import java.io.IOException


class MainActivity : AppCompatActivity() {
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

    private fun fetchTodos() {
        var NoteList: List<noteSata> = ArrayList()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://team-moon-api.deta.dev/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        val service = retrofit.create(NotesAPI::class.java)
        val jsonObject = JsonObject()

        val jsonobjectString = jsonObject.toString()
        val requestBody = jsonobjectString.toRequestBody("application/json".toMediaTypeOrNull())
        CoroutineScope(Dispatchers.IO).launch {

            // Do the POST request and get response
            try {
                val response = service.getToDO()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        initRecyclerview(arrayListOf(response.body().toString()))
                        Log.d("response", response.toString())
                    } else {

                        Log.e("RETROFIT_ERROR", response.code().toString())

                    }
                }

            } catch (e: IOException) {
                Log.e("mirror", e.message!!)
            }
        }

    }

    private fun initRecyclerview(noteList: java.util.ArrayList<String>) {
        val adapter = RecyclerAdapter(noteList)
        val layoutManager = GridLayoutManager(this, 2)
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = adapter
    }
}