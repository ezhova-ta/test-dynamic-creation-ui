package com.example.testdynamiccreationui.domain.models.ui_configuration

data class FormTextInput(
	val type: TextInputType,
	val caption: String,
	val attribute: String,
	val required: Boolean,
	val suggestions: Set<String>
)

enum class TextInputType(val text: String) {
	PLAIN_TEXT("plain-text"),
	AUTO_COMPLETE_TEXT_VIEW("auto-complete-text-view")
}
