package com.paltabrain.billing.mappers

interface Mapper<D, E> {

    fun mapToEntity(data: D): E

    fun mapToData(entity: E): D
}
