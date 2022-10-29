package com.example.testdynamiccreationui.data.repositories.mappers.api

import com.example.testdynamiccreationui.data.api.models.ui_configuration.*
import com.example.testdynamiccreationui.data.api.models.user.UserResponse
import com.example.testdynamiccreationui.domain.exceptions.GettingUserInfoException
import com.example.testdynamiccreationui.data.repositories.mappers.api.FormButtonDtoMapper.toDomain
import com.example.testdynamiccreationui.data.repositories.mappers.api.FormDtoMapper.toDomain
import com.example.testdynamiccreationui.data.repositories.mappers.api.FormTextInputDtoMapper.toDomain
import com.example.testdynamiccreationui.data.repositories.mappers.api.LayoutDtoMapper.toDomain
import com.example.testdynamiccreationui.data.repositories.mappers.api.UiConfigurationActivityDtoMapper.toDomain
import com.example.testdynamiccreationui.domain.models.ui_configuration.*
import com.example.testdynamiccreationui.domain.models.ui_configuration.FormButtonType.BUTTON
import com.example.testdynamiccreationui.domain.models.ui_configuration.TextInputType.AUTO_COMPLETE_TEXT_VIEW
import com.example.testdynamiccreationui.domain.models.ui_configuration.TextInputType.PLAIN_TEXT
import com.example.testdynamiccreationui.domain.models.user.User

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
		val domainTextInputType = when(type) {
			AUTO_COMPLETE_TEXT_VIEW.text -> AUTO_COMPLETE_TEXT_VIEW
			else -> PLAIN_TEXT
		}

		return FormTextInput(
			type = domainTextInputType,
			caption = caption,
			attribute = attribute,
			required = required,
			suggestions = suggestions ?: emptySet()
		)
	}
}

object FormButtonDtoMapper : DtoMapper<FormButtonDto, FormButton> {
	override fun FormButtonDto.toDomain(): FormButton {
		val domainButtonType = when(type) {
			else -> BUTTON
		}

		return FormButton(
			type = domainButtonType,
			caption = caption,
			formAction = formAction
		)
	}
}

object UserResponseMapper : DtoMapper<UserResponse, User> {
	@Throws(GettingUserInfoException::class)
	override fun UserResponse.toDomain(): User {
		if(error.isError) throw GettingUserInfoException(error.description)

		return with(data.user) {
			User(
				fullName = fullName,
				position = position,
				workHoursInMonth = workHoursInMonth,
				workedOutHours = workedOutHours
			)
		}
	}
}