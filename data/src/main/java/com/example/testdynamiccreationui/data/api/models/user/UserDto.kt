package com.example.testdynamiccreationui.data.api.models.user

data class UserDto(
	val fullName: String,
	val position: String,
	val workHoursInMonth: Int,
	val workedOutHours: Int
)