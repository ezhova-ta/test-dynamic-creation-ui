package com.example.testdynamiccreationui.domain.models

data class FormTextInput(
	val type: String,
	val caption: String,
	val attribute: String,
	val required: Boolean,
	val suggestions: List<String>
)
