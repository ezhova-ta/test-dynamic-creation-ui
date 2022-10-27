package com.example.testdynamiccreationui.domain.usecases

import com.example.testdynamiccreationui.domain.models.UiConfiguration
import com.example.testdynamiccreationui.domain.repositories.UserRepository
import javax.inject.Inject

class GetUiConfigurationUseCase @Inject constructor(
	private val userRepository: UserRepository
) {
	suspend operator fun invoke(): UiConfiguration {
		return userRepository.getUiConfiguration()
	}
}