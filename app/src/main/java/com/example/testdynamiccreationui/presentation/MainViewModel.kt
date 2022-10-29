package com.example.testdynamiccreationui.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testdynamiccreationui.di.DiScopes.APP_SCOPE
import com.example.testdynamiccreationui.di.DiScopes.MAIN_ACTIVITY_SCOPE
import com.example.testdynamiccreationui.di.DiScopes.MAIN_SCREEN_VIEW_MODEL_SCOPE
import com.example.testdynamiccreationui.domain.exceptions.GettingUserInfoException
import com.example.testdynamiccreationui.domain.models.ui_configuration.UiConfiguration
import com.example.testdynamiccreationui.domain.models.user.User
import com.example.testdynamiccreationui.domain.usecases.GetUiConfigurationUseCase
import com.example.testdynamiccreationui.domain.usecases.GetUserInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import toothpick.Toothpick
import javax.inject.Inject

class MainViewModel : ViewModel() {
	@Inject lateinit var getUiConfigurationUseCase: GetUiConfigurationUseCase
	@Inject lateinit var getUserInfoUseCase: GetUserInfoUseCase

	private val _uiConfigurationLiveData = MutableLiveData<UiConfiguration>()
	val uiConfigurationLiveData: LiveData<UiConfiguration>
		get() = _uiConfigurationLiveData

	private val _foundUserLiveData = MutableLiveData<User>()
	val foundUserLiveData: LiveData<User>
		get() = _foundUserLiveData

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
				val uiConfiguration = getUiConfigurationUseCase()
				_uiConfigurationLiveData.postValue(uiConfiguration)
			} catch(e: Exception) {
				// TODO Show error message
			}
		}
	}

	fun onButtonClick(action: String, params: Map<String, String>) {
		viewModelScope.launch(Dispatchers.IO) {
			try {
				val user = getUserInfoUseCase(action, params)
				_foundUserLiveData.postValue(user)
			} catch(e: GettingUserInfoException) {
				// TODO Show error message
			} catch(e: Exception) {
				// TODO Show error message
			}
		}
	}

	override fun onCleared() {
		Toothpick.closeScope(MAIN_SCREEN_VIEW_MODEL_SCOPE)
		super.onCleared()
	}
}