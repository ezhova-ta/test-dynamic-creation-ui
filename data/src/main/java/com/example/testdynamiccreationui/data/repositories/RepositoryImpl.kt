package com.example.testdynamiccreationui.data.repositories

import com.example.testdynamiccreationui.data.datasources.UserRemoteDataSource
import com.example.testdynamiccreationui.data.repositories.mappers.api.UiConfigurationResponseMapper.toDomain
import com.example.testdynamiccreationui.data.repositories.mappers.api.UserResponseMapper.toDomain
import com.example.testdynamiccreationui.domain.exceptions.GettingUserInfoException
import com.example.testdynamiccreationui.domain.models.ui_configuration.UiConfiguration
import com.example.testdynamiccreationui.domain.models.user.User
import com.example.testdynamiccreationui.domain.repositories.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
	private val remoteDataSource: UserRemoteDataSource
) : Repository {
	override suspend fun getUiConfiguration(): UiConfiguration {
		return remoteDataSource.getUiConfiguration().toDomain()
	}

	@Throws(GettingUserInfoException::class)
	override suspend fun getUserInfo(action: String, params: Map<String, String>): User {
		return remoteDataSource.getUserInfo(action, params).toDomain()
	}
}