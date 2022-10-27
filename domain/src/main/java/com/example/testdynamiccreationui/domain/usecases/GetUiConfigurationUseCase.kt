package com.example.testdynamiccreationui.domain.usecases

import com.example.testdynamiccreationui.domain.repositories.UserRepository
import javax.inject.Inject

class GetUiConfigurationUseCase @Inject constructor(
	private val userRepository: UserRepository
){

}