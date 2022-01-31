package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class AddNotesActivity : AppCompatActivity(), NoteService {

    lateinit var title: EditText
    lateinit var description: EditText
    lateinit var btn_save: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        title = findViewById(R.id.Et_title)
        description = findViewById(R.id.Et_description)
        btn_save = findViewById(R.id.btn_save)

        btn_save.setOnClickListener {

            sendData()
        }
    }

    private fun sendData() {
        val title = title.text.toString()
        val description = description.text.toString()

        val service = retrofit.create(NotesAPI::class.java)

        val jsonObject = JSONObject()
        jsonObject.put("title", title)
        jsonObject.put("description", description)

        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get response
            try {
                val response = service.postToDo(requestBody)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {

                        val mainActivity = MainActivity()
                        mainActivity.fetchTodos()
                        startActivity(Intent(this@AddNotesActivity, MainActivity::class.java))
                        finish()
                        Log.d("data", response.toString())
                    } else {
                        Log.e("RETROFIT_ERROR", response.code().toString())
                    }
                }
            } catch (e: IOException) {
                Log.e("mirror", e.message!!)
            }
        }
    }
}