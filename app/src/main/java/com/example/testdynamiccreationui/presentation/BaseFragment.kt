package com.example.testdynamiccreationui.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
	protected abstract val viewModel: BaseViewModel

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setupMessageShowing()
	}

	private fun setupMessageShowing() {
		viewModel.popupMessageEvent.observe(viewLifecycleOwner) { event ->
			event.getData()?.let { message -> showPopupMessage(message) }
		}
	}

	private fun showPopupMessage(text: Text) {
		Toast.makeText(
			requireActivity(),
			text.getText(requireContext()),
			Toast.LENGTH_LONG
		).show()
	}
}