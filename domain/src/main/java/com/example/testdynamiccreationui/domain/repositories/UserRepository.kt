package com.example.testdynamiccreationui.domain.repositories

import com.example.testdynamiccreationui.domain.models.UiConfiguration

interface UserRepository {
	suspend fun getUiConfiguration(): UiConfiguration
}