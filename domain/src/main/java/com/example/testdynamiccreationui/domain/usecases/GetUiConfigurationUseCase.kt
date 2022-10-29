package com.example.testdynamiccreationui.domain.usecases

import com.example.testdynamiccreationui.domain.models.UiConfiguration
import com.example.testdynamiccreationui.domain.repositories.Repository
import javax.inject.Inject

class GetUiConfigurationUseCase @Inject constructor(
	private val repository: Repository
) {
	suspend operator fun invoke(): UiConfiguration {
		return repository.getUiConfiguration()
	}
}