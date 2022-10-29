package com.example.testdynamiccreationui.data.api.models.ui_configuration

data class FormTextInputDto(
	val type: String,
	val caption: String,
	val attribute: String,
	val required: Boolean,
	val suggestions: Set<String>?
)
