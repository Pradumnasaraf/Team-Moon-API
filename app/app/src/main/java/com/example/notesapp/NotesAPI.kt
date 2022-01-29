package com.example.notesapp

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface NotesAPI {


    @POST("/create")
    suspend fun postToDo(@Body requestBody: RequestBody): Response<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("/todos")
    suspend fun getToDO(): Response<List<Notes>>

    @PUT("/update/{todoID}")
    suspend fun updateToDO(@Path("todoID")_id: String, requestBody: RequestBody): Response<ResponseBody>

    @DELETE("/delete/{todoID}")
    suspend fun deleteToDO(@Path("todoID") id: String): Response<ResponseBody>


}
