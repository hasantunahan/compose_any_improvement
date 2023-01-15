package com.example.compose_api.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_api.viewmodel.UserViewModel

@Composable
fun UserListView(viewModel: UserViewModel = hiltViewModel()) {
    if (viewModel.isLoading.value) {
        LoadingWidget(viewModel.isLoading.value)
    } else {
        var testStoreData = viewModel.collectAsStateOfTestValue().collectAsState(initial = "");
        Surface() {
            Column() {
                LazyColumn() {
                    items(viewModel.userList.value) {
                        Text(it.email)
                    }
                }
                if (testStoreData.value != "") {
                    Text(testStoreData.value)
                }
            }

        }
    }
}



@Composable
fun LoadingWidget(value: Boolean) {
    println("loading widget is running $value")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}