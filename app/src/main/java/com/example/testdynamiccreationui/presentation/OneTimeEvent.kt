package com.example.testdynamiccreationui.presentation

open class OneTimeEvent<T>(private val data: T) {
	private var hasBeenHandled = false

	fun getData(): T? {
		return if(hasBeenHandled) {
			null
		} else {
			hasBeenHandled = true
			data
		}
	}
}