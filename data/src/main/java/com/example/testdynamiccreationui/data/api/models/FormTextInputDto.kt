package com.example.testdynamiccreationui.data.api.models

data class FormTextInputDto(
	val type: String,
	val caption: String,
	val attribute: String,
	val required: Boolean,
	val suggestions: List<String>?
)
