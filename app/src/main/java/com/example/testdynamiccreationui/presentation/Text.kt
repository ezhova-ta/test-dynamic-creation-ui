package com.example.testdynamiccreationui.presentation

import android.content.Context
import androidx.annotation.StringRes

sealed class Text {
	abstract fun getText(context: Context): String

	object Empty : Text() {
		override fun getText(context: Context) = ""
	}

	class TextResource(@StringRes private val resId: Int) : Text() {
		override fun getText(context: Context): String =
			context.resources.getString(resId)
	}

	class TextString(private val text: String) : Text() {
		override fun getText(context: Context) = text
	}
}