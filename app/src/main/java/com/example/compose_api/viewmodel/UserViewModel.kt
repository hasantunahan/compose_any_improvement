package com.example.compose_api.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_api.constant.CacheConstant
import com.example.compose_api.service.model.User
import com.example.compose_api.service.repository.ReqresRepository
import com.example.compose_api.util.Either
import com.example.compose_api.util.cache_manager.CacheManager
import com.example.compose_api.util.extensions.safeToString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val reqresRepository: ReqresRepository,
    private val cacheManager: CacheManager,
) : ViewModel() {

    var userList = mutableStateOf<List<User>>(listOf())
    private var errorMessage = mutableStateOf<String>("")
    var isLoading = mutableStateOf<Boolean>(false)

    init {
        fetchAllUser()
    }

    fun collectAsStateOfTestValue(): Flow<String> {
        return cacheManager.getString(key = CacheConstant.testValue);
    }

    private fun fetchAllUser() {
        viewModelScope.launch {
            isLoading.value = true;
            when (val result = reqresRepository.getAllUsers()) {
                is Either.Success -> {
                    userList.value = result.data!!.data
                    isLoading.value = false
                    errorMessage.value = ""
                    cacheManager.setString(key = CacheConstant.testValue, value = "its ok!")
                }
                else -> {
                    errorMessage.value = "Fetch or convert error"
                    isLoading.value = false
                }
            }
        }
    }


}