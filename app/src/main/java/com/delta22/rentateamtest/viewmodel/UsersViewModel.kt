package com.delta22.rentateamtest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.delta22.rentateamtest.data.Repository
import com.delta22.rentateamtest.fragment.UsersFragment.LoadingState
import io.reactivex.disposables.Disposable

class UsersViewModel(val repository: Repository) : ViewModel() {

    val state = MutableLiveData<LoadingState>(LoadingState.Loading)

    private var disposable: Disposable? = null

    fun loadUsers() {
        disposable = repository.loadUsers().subscribe({
            state.value = LoadingState.Success(it)
        }, {
            state.value = LoadingState.Error(it)
        })
    }

    override fun onCleared() {
        disposable?.dispose()
    }
}
