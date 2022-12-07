package com.example.categoryselectiondzenru.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.categoryselectiondzenru.data.entity.Categories
import com.example.categoryselectiondzenru.data.repository.Repository
import kotlinx.coroutines.launch


class CatViewModel(private val repository: Repository) : ViewModel() {

    val allCategories: LiveData<List<Categories>> = repository.allCategories.asLiveData()


    fun insert(categories: Categories) = viewModelScope.launch {
        repository.insert(categories = categories)
    }

     fun deleteAll () = viewModelScope.launch {
         repository.deleteAll()
     }

     fun deleteByCategory (category: String) = viewModelScope.launch {
         repository.deleteByCategory(category = category)
     }

}


