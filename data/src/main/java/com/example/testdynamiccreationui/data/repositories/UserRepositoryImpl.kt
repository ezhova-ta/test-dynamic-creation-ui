package com.example.testdynamiccreationui.data.repositories

import com.example.testdynamiccreationui.data.datasources.UserRemoteDataSource
import com.example.testdynamiccreationui.data.repositories.mappers.api.UiConfigurationResponseMapper.toDomain
import com.example.testdynamiccreationui.domain.models.UiConfiguration
import com.example.testdynamiccreationui.domain.repositories.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
	private val remoteDataSource: UserRemoteDataSource
) : UserRepository {
	override suspend fun getUiConfiguration(): UiConfiguration {
		return remoteDataSource.getUiConfiguration().toDomain()
	}

}