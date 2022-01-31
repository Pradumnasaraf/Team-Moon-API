package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://team-moon-api.deta.dev/")

            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val service = retrofit.create(NotesAPI::class.java)
        val jsonObject = JsonObject()

        val jsonobjectString = jsonObject.toString()
        val requestBody = jsonobjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {

            // Do the POST request and get response
            val response = service.getToDO()


//            try {
//                val response = service.getToDO()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val json = response.body()
                    if (json != null) {
                        initRecyclerview(json)
                    }
                    //  initRecyclerview(notes)
                    Log.d("MainActivity", json.toString())
                }

//
//            } catch (e: IOException) {
//                Log.e("mirror", e.message!!)
//            }
            }

        }
    }

    private fun initRecyclerview(noteList: List<Notes>) {
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


