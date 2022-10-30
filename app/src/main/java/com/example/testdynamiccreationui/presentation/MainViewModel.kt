package com.example.testdynamiccreationui.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testdynamiccreationui.R
import com.example.testdynamiccreationui.di.DiScopes.APP_SCOPE
import com.example.testdynamiccreationui.di.DiScopes.MAIN_ACTIVITY_SCOPE
import com.example.testdynamiccreationui.di.DiScopes.MAIN_SCREEN_VIEW_MODEL_SCOPE
import com.example.testdynamiccreationui.domain.exceptions.GettingUserInfoException
import com.example.testdynamiccreationui.domain.models.ui_configuration.FormTextInput
import com.example.testdynamiccreationui.domain.models.ui_configuration.UiConfiguration
import com.example.testdynamiccreationui.domain.models.user.User
import com.example.testdynamiccreationui.domain.usecases.GetUiConfigurationUseCase
import com.example.testdynamiccreationui.domain.usecases.GetUserInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import toothpick.Toothpick
import javax.inject.Inject

class MainViewModel : BaseViewModel() {
	@Inject lateinit var getUiConfigurationUseCase: GetUiConfigurationUseCase
	@Inject lateinit var getUserInfoUseCase: GetUserInfoUseCase

	private lateinit var requiredParams: Set<String>
	val enteredParams = mutableMapOf<String, String>()

	private val _uiConfigurationLiveData = MutableLiveData<UiConfiguration>()
	val uiConfigurationLiveData: LiveData<UiConfiguration>
		get() = _uiConfigurationLiveData

	private val _foundUserLiveData = MutableLiveData<User>()
	val foundUserLiveData: LiveData<User>
		get() = _foundUserLiveData

	init {
		bindDiScope()
		getUiConfiguration()
	}

	override fun bindDiScope() {
		val mainScreenScope = Toothpick.openScopes(
			APP_SCOPE,
			MAIN_ACTIVITY_SCOPE,
			MAIN_SCREEN_VIEW_MODEL_SCOPE
		)
		Toothpick.inject(this, mainScreenScope)
	}

	private fun getUiConfiguration() {
		viewModelScope.launch(Dispatchers.IO) {
			try {
				val uiConfiguration = getUiConfigurationUseCase()
				_uiConfigurationLiveData.postValue(uiConfiguration)
				requiredParams = uiConfiguration.getTextInputs().getRequiredAttributes()
			} catch(e: Exception) {
				showMessage(Text.TextResource(R.string.error_tyr_again_later))
			}
		}
	}

	private fun UiConfiguration.getTextInputs(): List<FormTextInput> {
		// TODO Temp (first activity)
		return activities.firstOrNull()?.layout?.form?.text ?: emptyList()
	}

	private fun List<FormTextInput>.getRequiredAttributes(): Set<String> {
		return filter { it.required }.map { it.attribute }.toSet()
	}

	fun onButtonClick(action: String) {
		viewModelScope.launch(Dispatchers.IO) {
			try {
				if(isAllRequiredParamsFilled()) {
					val user = getUserInfoUseCase(action, enteredParams)
					_foundUserLiveData.postValue(user)
				} else {
					showMessage(Text.TextResource(R.string.fill_in_required_fields_message))
				}
			} catch(e: GettingUserInfoException) {
				showMessage(Text.TextString(e.description))
			} catch(e: Exception) {
				showMessage(Text.TextResource(R.string.failed_to_get_employee_information_message))
			}
		}
	}

	private fun isAllRequiredParamsFilled(): Boolean {
		return enteredParams.keys.containsAll(requiredParams)
	}

	override fun onCleared() {
		Toothpick.closeScope(MAIN_SCREEN_VIEW_MODEL_SCOPE)
		super.onCleared()
	}
}