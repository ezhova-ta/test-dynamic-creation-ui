package com.example.testdynamiccreationui.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testdynamiccreationui.di.DiScopes.APP_SCOPE
import com.example.testdynamiccreationui.di.DiScopes.MAIN_ACTIVITY_SCOPE
import com.example.testdynamiccreationui.di.DiScopes.MAIN_SCREEN_VIEW_MODEL_SCOPE
import com.example.testdynamiccreationui.domain.models.UiConfiguration
import com.example.testdynamiccreationui.domain.usecases.GetUiConfigurationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import toothpick.Toothpick
import javax.inject.Inject

class MainViewModel : ViewModel() {
	@Inject lateinit var getUiConfigurationUseCase: GetUiConfigurationUseCase

	private val _uiConfigurationLiveData = MutableLiveData<UiConfiguration>()
	val uiConfigurationLiveData: LiveData<UiConfiguration>
		get() = _uiConfigurationLiveData

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

	fun onScreenCreated() {
		getUiConfiguration()
	}

	private fun getUiConfiguration() {
		viewModelScope.launch(Dispatchers.IO) {
			try {
				val uiConfiguration = getUiConfigurationUseCase.invoke()
				_uiConfigurationLiveData.postValue(uiConfiguration)
			} catch(e: Exception) {
				// TODO Show error message
			}
		}
	}

	fun onButtonClick(formAction: String, attributes: Map<String, String>) {
		TODO()
	}

	override fun onCleared() {
		Toothpick.closeScope(MAIN_SCREEN_VIEW_MODEL_SCOPE)
		super.onCleared()
	}
}