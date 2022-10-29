package com.example.testdynamiccreationui.domain.repositories

import com.example.testdynamiccreationui.domain.models.UiConfiguration

interface Repository {
	suspend fun getUiConfiguration(): UiConfiguration
}