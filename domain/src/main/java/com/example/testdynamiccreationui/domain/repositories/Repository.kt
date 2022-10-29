package com.example.testdynamiccreationui.domain.repositories

import com.example.testdynamiccreationui.domain.models.ui_configuration.UiConfiguration
import com.example.testdynamiccreationui.domain.models.user.User

interface Repository {
	suspend fun getUiConfiguration(): UiConfiguration
	suspend fun getUserInfo(action: String, params: Map<String, String>): User
}