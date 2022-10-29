package com.example.testdynamiccreationui.presentation

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.testdynamiccreationui.R
import com.example.testdynamiccreationui.domain.models.FormButton
import com.example.testdynamiccreationui.domain.models.FormTextInput
import com.example.testdynamiccreationui.domain.models.TextInputType.AUTO_COMPLETE_TEXT_VIEW
import com.example.testdynamiccreationui.domain.models.UiConfiguration

class MainFragment : Fragment() {
	private val viewModel: MainViewModel by viewModels()

	override fun onAttach(context: Context) {
		super.onAttach(context)
		viewModel.onScreenCreated()
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		return ConstraintLayout(requireContext()).apply {
			id = R.id.screenContainer
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewModel.uiConfigurationLiveData.observe(viewLifecycleOwner) { uiConfiguration ->
			showUi(uiConfiguration)
		}
	}

	private fun showUi(uiConfiguration: UiConfiguration) {
		// TODO Temp (first activity)
		val uiConfigurationActivity = uiConfiguration.activities.firstOrNull() ?: return
		val headerView = createHeaderView(uiConfigurationActivity.layout.header)
		val inputsView = createTextInputsView(headerView.id, uiConfigurationActivity.layout.form.text)
		(view as ConstraintLayout).apply {
			addView(headerView)
			addView(inputsView)
		}
	}

	private fun createHeaderView(header: String): TextView {
		val layoutParams = ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
			topToTop = PARENT_ID
			startToStart = PARENT_ID
			endToEnd = PARENT_ID
		}

		return TextView(requireContext()).apply {
			id = R.id.fragmentHeaderView
			text = header
			textSize = 20f // TODO Text size in sp
			this.layoutParams = layoutParams
		}
	}

	private fun createTextInputsView(viewOnTopId: Int, textInputs: List<FormTextInput>): LinearLayout {
		val layoutParams = ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
			topToBottom = viewOnTopId
			topMargin = 16 // TODO Margin in dp
		}
		return LinearLayout(requireContext()).apply {
			id = R.id.textInputsView
			this.layoutParams = layoutParams
			orientation = LinearLayout.VERTICAL
			addTextInputViews(textInputs)
		}
	}

	private fun LinearLayout.addTextInputViews(textInputs: List<FormTextInput>) {
		textInputs.forEach { textInput ->
			val textInputView = when(textInput.type) {
				AUTO_COMPLETE_TEXT_VIEW -> createAutoCompleteTextInputView(textInput)
				else -> createPlainTextInputView(textInput)
			}
			addView(textInputView)
		}
	}

	private fun createAutoCompleteTextInputView(textInput: FormTextInput): AutoCompleteTextView {
		val adapter = ArrayAdapter(
			requireContext(),
			// TODO Temp android.R.layout.simple_dropdown_item_1line
			android.R.layout.simple_dropdown_item_1line,
			textInput.suggestions ?: emptyList()
		)

		return AutoCompleteTextView(requireContext()).apply {
			setAdapter(adapter)
			hint = textInput.caption
		}
	}

	private fun createPlainTextInputView(textInput: FormTextInput): EditText {
		return EditText(requireContext()).apply {
			inputType = InputType.TYPE_CLASS_TEXT
			hint = textInput.caption
		}
	}

	private fun LinearLayout.addButtonViews(buttons: List<FormButton>) {
		TODO()
	}
}