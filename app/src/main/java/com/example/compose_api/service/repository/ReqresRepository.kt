package com.example.compose_api.service.repository

import com.example.compose_api.constant.ErrorTypeConstant
import com.example.compose_api.service.ReqresApi
import com.example.compose_api.service.model.ResponseModel
import com.example.compose_api.service.model.User
import com.example.compose_api.util.Either
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ReqresRepository @Inject constructor(
    private val api: ReqresApi
) {

    suspend fun getAllUsers(): Either<ResponseModel<List<User>>> {
        return try {
            var result = api.getAllUsers();
            return Either.Success(result)
        } catch (e: Exception) {
            return Either.Error(message = ErrorTypeConstant.fetchError)
        }
    }

    suspend fun getUserById(id: Int): Either<User> {
        return try {
            return Either.Success(api.getUserDetailById(id))
        } catch (e: Exception) {
            return Either.Error(message =  ErrorTypeConstant.fetchError)
        }
    }

}