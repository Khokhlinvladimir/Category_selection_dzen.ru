package com.example.categoryselectiondzenru.presentation.adapter.mapper

import com.example.categoryselectiondzenru.presentation.adapter.Tag

abstract class Mapper <Entity>{

    abstract fun mapFromEntity(entity: Entity): Tag

    abstract fun mapToEntity(tag: Tag): Entity

    fun mapFromEntityList(entities: List<Entity>): List<Tag>{
        return entities.map { mapFromEntity(it) }
    }

    fun mapToEntityList(tags: List<Tag>): List<Entity>{
        return tags.map { mapToEntity(it) }
    }
}