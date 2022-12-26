package com.example.categoryselectiondzenru.data.repository

import androidx.annotation.WorkerThread
import com.example.categoryselectiondzenru.data.db.CategoriesDao
import com.example.categoryselectiondzenru.data.entity.Categories
import com.example.categoryselectiondzenru.model.Category
import kotlinx.coroutines.flow.Flow

class Repository(private val categoriesDao: CategoriesDao) {

    val allCategories: Flow<List<Categories>> = categoriesDao.getCategories()

    @WorkerThread
    suspend fun insert(categories: Categories) {
        categoriesDao.insert(categories = categories)
    }

    @WorkerThread
    suspend fun deleteAll() {
        categoriesDao.deleteAll()
    }

    @WorkerThread
    suspend fun deleteByCategory(category: String) {
        categoriesDao.deleteByCategory(category)
    }

    val listCategories: MutableList<Category>
        get() {
            return mutableListOf(
               Category( "Юмор"),
               Category("Еда"),
                Category("Кино"),
                Category("Рестораны"),
                Category("Прогулки"),
                Category("Политика"),
                Category("Новости"),
                Category("Автомобили"),
                Category("Сериалы"),
                Category("Рецепты"),
                Category("Работа"),
                Category("Отдых"),
                Category("Спорт"),
                Category("Политика"),
                Category("Новости"),
                Category("Юмор"),
                Category("Еда"),
                Category("Кино"),
                Category("Рестораны"),
                Category("Прогулки"),
                Category("Политика"),
                Category("Юмор")
            )
        }
}