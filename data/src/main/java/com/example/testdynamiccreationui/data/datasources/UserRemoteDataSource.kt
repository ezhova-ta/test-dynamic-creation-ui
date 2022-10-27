package com.example.testdynamiccreationui.data.datasources

import com.example.testdynamiccreationui.data.api.UserApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRemoteDataSource @Inject constructor(
	private val service: UserApiService
) {

}