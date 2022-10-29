package com.example.testdynamiccreationui.domain.models.ui_configuration

data class FormButton(
	val type: FormButtonType,
	val caption: String,
	val formAction: String
)

enum class FormButtonType(val text: String) {
	BUTTON("button")
}
