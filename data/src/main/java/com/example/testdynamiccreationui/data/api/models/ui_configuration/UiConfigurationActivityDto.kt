package com.example.testdynamiccreationui.data.api.models.ui_configuration

import com.google.gson.annotations.SerializedName

data class UiConfigurationActivityDto(
	@SerializedName("activity")
	val name: String,
	val layout: LayoutDto
)
