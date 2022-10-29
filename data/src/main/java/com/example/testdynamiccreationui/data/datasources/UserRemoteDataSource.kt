package com.example.testdynamiccreationui.data.datasources

import com.example.testdynamiccreationui.data.api.UserApiService
import com.example.testdynamiccreationui.data.api.models.ui_configuration.UiConfigurationResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRemoteDataSource @Inject constructor(
	private val service: UserApiService
) {
	suspend fun getUiConfiguration(): UiConfigurationResponse {
		return service.getUiConfiguration()
	}
}