package com.example.myapplication.presentation

import androidx.lifecycle.*
import com.example.myapplication.core.Resource
import com.example.myapplication.repository.Repository
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repository: Repository) : ViewModel() {

    var query = ""

    fun searchProductByName() = liveData( Dispatchers.IO ){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.getProductByName(query)))
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }
}

class MainViewModelFactory (private val repo: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repository::class.java).newInstance(repo)
    }
}

