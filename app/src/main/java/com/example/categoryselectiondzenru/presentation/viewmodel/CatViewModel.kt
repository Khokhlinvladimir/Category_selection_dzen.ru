package com.example.categoryselectiondzenru.presentation.viewmodel

import androidx.lifecycle.*
import com.example.categoryselectiondzenru.data.entity.Categories
import com.example.categoryselectiondzenru.data.repository.Repository
import com.example.categoryselectiondzenru.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

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


