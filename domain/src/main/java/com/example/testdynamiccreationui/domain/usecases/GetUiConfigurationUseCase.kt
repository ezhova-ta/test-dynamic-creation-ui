package com.example.testdynamiccreationui.domain.usecases

import com.example.testdynamiccreationui.domain.models.ui_configuration.Layout
import com.example.testdynamiccreationui.domain.models.ui_configuration.UiConfiguration
import com.example.testdynamiccreationui.domain.repositories.Repository
import javax.inject.Inject

class GetUiConfigurationUseCase @Inject constructor(
	private val repository: Repository
) {
	suspend operator fun invoke(): Layout? = repository
		.getUiConfiguration()
		.activities
		.firstOrNull()
		?.layout
}