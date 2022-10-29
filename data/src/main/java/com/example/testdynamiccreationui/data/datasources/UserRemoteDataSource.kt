package com.example.testdynamiccreationui.data.datasources

import com.example.testdynamiccreationui.data.api.UserApiService
import com.example.testdynamiccreationui.data.api.models.ui_configuration.UiConfigurationResponse
import com.example.testdynamiccreationui.data.api.models.user.UserResponse
import com.example.testdynamiccreationui.domain.models.user.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRemoteDataSource @Inject constructor(
	private val service: UserApiService
) {
	suspend fun getUiConfiguration(): UiConfigurationResponse {
		return service.getUiConfiguration()
	}

	suspend fun getUserInfo(action: String, params: Map<String, String>): UserResponse {
		return service.getUserInfo(action, params)
	}
}