package com.delta22.rentateamtest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.delta22.rentateamtest.data.Repository
import javax.inject.Inject

class UsersModelFactory @Inject constructor(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == UsersViewModel::class.java) {
            return UsersViewModel(repository) as T
        }
        throw Exception("Illegal ViewModel $modelClass")
    }
}
