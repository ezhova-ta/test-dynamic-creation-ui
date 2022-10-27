package com.example.testdynamiccreationui.di.providers

import com.example.testdynamiccreationui.data.UserApiRetrofitBuilder
import com.example.testdynamiccreationui.data.api.UserApiService
import javax.inject.Inject
import javax.inject.Provider

class UserApiServiceProvider @Inject constructor(
	private val retrofitBuilder: UserApiRetrofitBuilder
): Provider<UserApiService> {
	override fun get(): UserApiService {
		val retrofit = retrofitBuilder.build()
		return retrofit.create(UserApiService::class.java)
	}
}