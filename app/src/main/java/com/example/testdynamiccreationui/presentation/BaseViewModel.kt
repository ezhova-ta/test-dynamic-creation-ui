package com.example.testdynamiccreationui.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
	private val _popupMessageEvent = MutableLiveData<OneTimeEvent<Text>>()
	val popupMessageEvent: LiveData<OneTimeEvent<Text>>
		get() = _popupMessageEvent

	protected abstract fun bindDiScope()

	protected fun showMessage(text: Text) {
		_popupMessageEvent.postValue(OneTimeEvent(text))
	}
}