package com.example.compose_api.service

import com.example.compose_api.service.model.ResponseModel
import com.example.compose_api.service.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ReqresApi {

    @GET(ApiPath.users)
    suspend fun getAllUsers() : ResponseModel<List<User>>;

    @GET("${ApiPath.users}/{id}")
    suspend fun getUserDetailById(
        @Path(value = "id") id : Int
    ): User
}