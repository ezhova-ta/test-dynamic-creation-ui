package com.example.testdynamiccreationui.data.repositories

import com.example.testdynamiccreationui.data.datasources.UserRemoteDataSource
import com.example.testdynamiccreationui.domain.repositories.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
	private val remoteDataSource: UserRemoteDataSource
) : UserRepository {

}