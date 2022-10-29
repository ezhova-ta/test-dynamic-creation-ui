package com.example.testdynamiccreationui.data.api

import com.example.testdynamiccreationui.data.api.models.ui_configuration.UiConfigurationResponse
import com.example.testdynamiccreationui.data.api.models.user.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface UserApiService {
	@GET("test.json")
	suspend fun getUiConfiguration(): UiConfigurationResponse

	@GET("{action}")
	suspend fun getUserInfo(
		@Path("action") action: String,
		@QueryMap params: Map<String, String>
	): UserResponse
}