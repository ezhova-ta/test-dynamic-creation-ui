package com.example.testdynamiccreationui.data.api

import com.example.testdynamiccreationui.data.api.models.UiConfigurationResponse
import retrofit2.http.GET

interface UserApiService {
	@GET("test.json")
	suspend fun getUiConfiguration(): UiConfigurationResponse
}