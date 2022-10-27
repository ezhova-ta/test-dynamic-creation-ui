package com.example.testdynamiccreationui.presentation

import android.app.Application
import com.example.testdynamiccreationui.di.AppModule
import com.example.testdynamiccreationui.di.DiScopes
import toothpick.Toothpick

class App : Application() {
	companion object {
		internal lateinit var INSTANCE: App
			private set
	}

	override fun onCreate() {
		super.onCreate()
		INSTANCE = this
		injectAppScope()
	}

	private fun injectAppScope() {
		Toothpick.openScope(DiScopes.APP_SCOPE).installModules(AppModule(INSTANCE))
	}
}