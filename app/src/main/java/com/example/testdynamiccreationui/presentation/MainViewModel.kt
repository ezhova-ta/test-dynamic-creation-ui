package com.example.testdynamiccreationui.presentation

import androidx.lifecycle.ViewModel
import com.example.testdynamiccreationui.di.DiScopes.APP_SCOPE
import com.example.testdynamiccreationui.di.DiScopes.MAIN_ACTIVITY_SCOPE
import com.example.testdynamiccreationui.di.DiScopes.MAIN_SCREEN_VIEW_MODEL_SCOPE
import toothpick.Toothpick

class MainViewModel : ViewModel() {
	init {
		bindDiScope()
	}

	private fun bindDiScope() {
		val mainScreenScope = Toothpick.openScopes(
			APP_SCOPE,
			MAIN_ACTIVITY_SCOPE,
			MAIN_SCREEN_VIEW_MODEL_SCOPE
		)
		Toothpick.inject(this, mainScreenScope)
	}

	override fun onCleared() {
		Toothpick.closeScope(MAIN_SCREEN_VIEW_MODEL_SCOPE)
		super.onCleared()
	}
}