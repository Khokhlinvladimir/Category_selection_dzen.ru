package com.example.categoryselectiondzenru.presentation

import androidx.lifecycle.*
import com.example.categoryselectiondzenru.data.entity.Categories
import com.example.categoryselectiondzenru.data.repository.Repository
import com.example.categoryselectiondzenru.presentation.adapter.data.Category
import kotlinx.coroutines.launch


class CatViewModel(private val repository: Repository) : ViewModel() {

    val dataBaseCategories: LiveData<List<Categories>> = repository.allCategories.asLiveData()
    private val listCategories = MutableLiveData<List<Category>>()
    val getListCategories: LiveData<List<Category>> get() = listCategories

    init {
        listCategories()
    }

    fun insert(categories: Categories) = viewModelScope.launch {
        repository.insert(categories = categories)
    }

     fun deleteAll () = viewModelScope.launch {
         repository.deleteAll()
     }

     fun deleteByCategory (category: String) = viewModelScope.launch {
         repository.deleteByCategory(category = category)
     }

    private fun listCategories(){
        listCategories.value = repository.listCategories
    }


}


