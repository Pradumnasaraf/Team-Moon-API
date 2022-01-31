package com.example.notesapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import java.io.IOException

class EditActivity : AppCompatActivity() {

    lateinit var et_description: EditText
    lateinit var et_title: EditText
    lateinit var btn_update: Button
    lateinit var btn_delete: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        et_description = findViewById(R.id.description_et)
        et_title = findViewById(R.id.title_et)
        btn_update = findViewById(R.id.btn_update)
        btn_delete = findViewById(R.id.btn_delete)
        // val intent= Intent()
        val bundle: Bundle? = intent.extras
        val _id = bundle?.getString("_id")
        val title = bundle?.getString("title")
        val description = bundle?.getString("description")

        et_description.setText(description)
        et_title.setText(title)
        btn_update.setOnClickListener {
            val updatedtitle = et_title.text.toString()
            val updateddescription = et_description.text.toString()
            updateNote(updatedtitle, updateddescription, _id)
        }
        btn_delete.setOnClickListener {
            deleteNote(_id)
        }
    }

    private fun deleteNote(_id: String?) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://team-moon-api.deta.dev/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        val service = retrofit.create(NotesAPI::class.java)


        CoroutineScope(Dispatchers.IO).launch {

            // Do the POST request and get response
            try {
                val response =
                    _id?.let { service.deleteToDO(it) }
                if (response != null) {
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {
                            Toast.makeText(
                                this@EditActivity,
                                "DELETED", LENGTH_SHORT
                            )
                                .show()
                            Log.d("data", response.toString())
                        } else {
                            Toast.makeText(
                                this@EditActivity,
                                "ERROR, Please SEE LogCat",
                                LENGTH_SHORT
                            )
                                .show()

                            Log.e("RETROFIT_ERROR", response.code().toString())

                        }
                    }
                }

            } catch (e: IOException) {
                Log.e("mirror", e.message!!)
            }
        }

    }

    private fun updateNote(title: String?, description: String?, _id: String?) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://team-moon-api.deta.dev/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        val service = retrofit.create(NotesAPI::class.java)


        val jsonObject = JSONObject()
        val jsonArray = JSONArray()
        jsonArray.put(_id)

        val id: String? = _id
        jsonArray.put(jsonObject.put("title", title))

        jsonArray.put(jsonObject.put("description", description))
        //jsonObject.put("description", description)
        val jsonObjectString = jsonArray.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
        CoroutineScope(Dispatchers.IO).launch {

            // Do the POST request and get response
            try {
                val response = id?.let { service.updateToDO(it, requestBody) }
                if (response != null) {
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {

                            Log.d("data", response.toString())
                        } else {

                            Log.e("RETROFIT_ERROR", response.code().toString())

                        }
                    }
                }

            } catch (e: IOException) {
                Log.e("mirror", e.message!!)
            }
        }


    }
}