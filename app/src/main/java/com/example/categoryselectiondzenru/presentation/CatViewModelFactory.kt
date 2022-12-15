package com.example.categoryselectiondzenru.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.categoryselectiondzenru.data.repository.Repository

class CatViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

   override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if (modelClass.isAssignableFrom(CatViewModel::class.java)) {
           @Suppress("UNCHECKED_CAST")
           return CatViewModel(repository) as T
       }
       throw IllegalArgumentException("Unknown ViewModel class")
   }


    }

