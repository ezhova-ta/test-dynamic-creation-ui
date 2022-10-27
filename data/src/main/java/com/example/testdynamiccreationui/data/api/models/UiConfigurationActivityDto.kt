package com.example.testdynamiccreationui.data.api.models

import com.google.gson.annotations.SerializedName

data class UiConfigurationActivityDto(
	@SerializedName("activity")
	val name: String,
	val layout: LayoutDto
)
