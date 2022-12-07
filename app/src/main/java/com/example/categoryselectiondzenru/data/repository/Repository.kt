package com.example.categoryselectiondzenru.data.repository

import androidx.annotation.WorkerThread
import com.example.categoryselectiondzenru.data.db.CategoriesDao
import com.example.categoryselectiondzenru.data.entity.Categories
import kotlinx.coroutines.flow.Flow

class Repository(private val categoriesDao: CategoriesDao) {

    val allCategories: Flow<List<Categories>> = categoriesDao.getCategories()

    @WorkerThread
    suspend fun insert(category: Categories) {
        categoriesDao.insert(category)
    }

    @WorkerThread
    suspend fun deleteAll() {
        categoriesDao.deleteAll()
    }

    @WorkerThread
    suspend fun deleteByCategory(category: String) {
        categoriesDao.deleteByCategory(category)
    }


}