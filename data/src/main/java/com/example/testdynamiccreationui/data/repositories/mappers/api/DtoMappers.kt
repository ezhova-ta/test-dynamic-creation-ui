package com.example.testdynamiccreationui.data.repositories.mappers.api

import com.example.testdynamiccreationui.data.api.models.*
import com.example.testdynamiccreationui.data.repositories.mappers.api.FormButtonDtoMapper.toDomain
import com.example.testdynamiccreationui.data.repositories.mappers.api.FormDtoMapper.toDomain
import com.example.testdynamiccreationui.data.repositories.mappers.api.FormTextInputDtoMapper.toDomain
import com.example.testdynamiccreationui.data.repositories.mappers.api.LayoutDtoMapper.toDomain
import com.example.testdynamiccreationui.data.repositories.mappers.api.UiConfigurationActivityDtoMapper.toDomain
import com.example.testdynamiccreationui.domain.models.*

object UiConfigurationResponseMapper : DtoMapper<UiConfigurationResponse, UiConfiguration> {
	override fun UiConfigurationResponse.toDomain(): UiConfiguration {
		return UiConfiguration(activities = activities.toDomain())
	}

	private fun List<UiConfigurationActivityDto>.toDomain(): List<UiConfigurationActivity> {
		return map { it.toDomain() }
	}
}

object UiConfigurationActivityDtoMapper : DtoMapper<UiConfigurationActivityDto, UiConfigurationActivity> {
	override fun UiConfigurationActivityDto.toDomain(): UiConfigurationActivity {
		return UiConfigurationActivity(name = name, layout = layout.toDomain())
	}
}

object LayoutDtoMapper : DtoMapper<LayoutDto, Layout> {
	override fun LayoutDto.toDomain(): Layout {
		return Layout(header = header, form = form.toDomain())
	}
}

object FormDtoMapper : DtoMapper<FormDto, Form> {
	override fun FormDto.toDomain(): Form {
		return Form(
			text = text.toFormTextInputList(),
			buttons = buttons.toFormButtonList()
		)
	}

	private fun List<FormTextInputDto>.toFormTextInputList(): List<FormTextInput> {
		return map { it.toDomain() }
	}

	private fun List<FormButtonDto>.toFormButtonList(): List<FormButton> {
		return map { it.toDomain() }
	}
}

object FormTextInputDtoMapper : DtoMapper<FormTextInputDto, FormTextInput> {
	override fun FormTextInputDto.toDomain(): FormTextInput {
		return FormTextInput(
			type = type,
			caption = caption,
			attribute = attribute,
			required = required,
			suggestions = suggestions
		)
	}
}

object FormButtonDtoMapper : DtoMapper<FormButtonDto, FormButton> {
	override fun FormButtonDto.toDomain(): FormButton {
		return FormButton(
			type = type,
			caption = caption,
			formAction = formAction
		)
	}
}