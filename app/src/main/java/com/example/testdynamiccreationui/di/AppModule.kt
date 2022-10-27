package com.example.testdynamiccreationui.di

import android.content.Context
import com.example.testdynamiccreationui.data.api.UserApiService
import com.example.testdynamiccreationui.data.repositories.UserRepositoryImpl
import com.example.testdynamiccreationui.di.providers.OkHttpClientProvider
import com.example.testdynamiccreationui.di.providers.UserApiServiceProvider
import com.example.testdynamiccreationui.domain.repositories.UserRepository
import com.example.testdynamiccreationui.presentation.App
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import toothpick.config.Module

class AppModule(application: App) : Module() {
	init {
		bind(Context::class.java).toInstance(application.applicationContext)
		bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java)
		bind(Converter.Factory::class.java).toInstance(GsonConverterFactory.create())
		bind(UserApiService::class.java).toProvider(UserApiServiceProvider::class.java).singleton()
		bind(UserRepository::class.java).to(UserRepositoryImpl::class.java)
	}
}