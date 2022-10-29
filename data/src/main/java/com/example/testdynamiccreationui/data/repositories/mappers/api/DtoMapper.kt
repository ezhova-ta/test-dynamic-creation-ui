package com.example.testdynamiccreationui.data.repositories.mappers.api

/**
 * Converts a data transfer object to a domain entity
 *
 * @param T data transfer object type
 * @param R domain entity type
 */
sealed interface DtoMapper<T, R> {
	fun T.toDomain(): R
}