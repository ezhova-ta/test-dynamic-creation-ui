package com.example.testdynamiccreationui.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.*
import androidx.core.view.setPadding
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testdynamiccreationui.R
import com.example.testdynamiccreationui.domain.models.ui_configuration.FormButton
import com.example.testdynamiccreationui.domain.models.ui_configuration.FormButtonType.BUTTON
import com.example.testdynamiccreationui.domain.models.ui_configuration.FormTextInput
import com.example.testdynamiccreationui.domain.models.ui_configuration.Layout
import com.example.testdynamiccreationui.domain.models.ui_configuration.TextInputType.AUTO_COMPLETE_TEXT_VIEW
import com.example.testdynamiccreationui.domain.models.ui_configuration.TextInputType.PLAIN_TEXT
import com.example.testdynamiccreationui.domain.models.user.User
import com.example.testdynamiccreationui.presentation.adapters.UserInfoAdapter
import com.example.testdynamiccreationui.presentation.utils.convertDpToPx

class MainFragment : BaseFragment() {
	override val viewModel: MainViewModel by viewModels()
	private lateinit var userInfoAdapter: UserInfoAdapter

	companion object {
		private const val REQUIRED_INPUT_SYMBOL = "*"
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		return ConstraintLayout(requireContext()).apply { id = R.id.screenContainer }
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewModel.layoutLiveData.removeObservers(viewLifecycleOwner)
		viewModel.foundUserLiveData.removeObservers(viewLifecycleOwner)
		viewModel.layoutLiveData.observe(viewLifecycleOwner) { showUi(it) }
		viewModel.foundUserLiveData.observe(viewLifecycleOwner) { showUserInfo(it) }
	}

	private fun showUi(layout: Layout?) {
		layout ?: return
		val headerView = createHeaderView(layout.header)
		val inputsView = createTextInputsView(headerView.id, layout.form.text)
		val buttonsView = createButtonsView(inputsView.id, layout.form.buttons)
		val userView = createUserView(buttonsView.id)
		with(getFragmentContainer()) {
			addView(headerView)
			addView(inputsView)
			addView(buttonsView)
			addView(userView)
		}
	}

	private fun getFragmentContainer(): ViewGroup {
		return (view as ConstraintLayout).apply {
			setPadding(convertDpToPx(requireContext(), 8))
			removeAllViews()
		}
	}

	private fun createHeaderView(header: String): View {
		val layoutParams = ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
			topToTop = PARENT_ID
			startToStart = PARENT_ID
			endToEnd = PARENT_ID
		}

		return TextView(requireContext()).apply {
			id = R.id.fragmentHeaderView
			text = header
			setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
			this.layoutParams = layoutParams
		}
	}

	private fun createTextInputsView(viewOnTopId: Int, textInputs: List<FormTextInput>): View {
		val layoutParams = ConstraintLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
			topToBottom = viewOnTopId
			topMargin = convertDpToPx(requireContext(), 8)
		}
		return LinearLayout(requireContext()).apply {
			id = R.id.textInputsView
			this.layoutParams = layoutParams
			orientation = LinearLayout.VERTICAL
			addTextInputViews(textInputs)
		}
	}

	private fun LinearLayout.addTextInputViews(textInputs: List<FormTextInput>) {
		textInputs.forEach { textInput -> addView(createTextInputView(textInput)) }
	}

	private fun createTextInputView(textInput: FormTextInput): View {
		val textInputView = when(textInput.type) {
			AUTO_COMPLETE_TEXT_VIEW -> createAutoCompleteTextInputView(textInput)
			PLAIN_TEXT -> createPlainTextInputView()
		}.apply {
			hint = textInput.caption
			setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
			setText(viewModel.enteredParams[textInput.attribute] , TextView.BufferType.EDITABLE)
			setTextChangedListener(textInput.attribute)
		}

		return if(textInput.required) {
			createRequiredTextInputView(textInputView)
		} else {
			textInputView
		}
	}

	private fun EditText.setTextChangedListener(parameter: String) {
		addTextChangedListener { editable ->
			val text = editable?.toString()
			if(text.isNullOrEmpty()) {
				viewModel.enteredParams.remove(parameter)
			} else {
				viewModel.enteredParams[parameter] = text
			}
		}
	}

	private fun createRequiredTextInputView(textInputView: View): View {
		val requiredSymbolView = TextView(requireContext()).apply { text = REQUIRED_INPUT_SYMBOL }
		textInputView.layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply { weight = 1f }
		return LinearLayout(requireContext()).apply {
			orientation = LinearLayout.HORIZONTAL
			addView(requiredSymbolView)
			addView(textInputView)
		}
	}

	private fun createAutoCompleteTextInputView(textInput: FormTextInput): AutoCompleteTextView {
		val adapter = ArrayAdapter(
			requireContext(),
			android.R.layout.simple_dropdown_item_1line,
			textInput.suggestions.toTypedArray()
		)

		return AutoCompleteTextView(requireContext()).apply { setAdapter(adapter) }
	}

	private fun createPlainTextInputView(): EditText {
		return EditText(requireContext()).apply { inputType = InputType.TYPE_CLASS_TEXT }
	}

	private fun createButtonsView(viewOnTopId: Int, buttons: List<FormButton>): View {
		val layoutParams = ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
			topToBottom = viewOnTopId
			startToStart = PARENT_ID
			endToEnd = PARENT_ID
			topMargin = convertDpToPx(requireContext(), 8)
		}

		return LinearLayout(requireContext()).apply {
			id = R.id.buttonsView
			this.layoutParams = layoutParams
			orientation = LinearLayout.VERTICAL
			addButtonViews(buttons)
		}
	}

	private fun LinearLayout.addButtonViews(buttons: List<FormButton>) {
		buttons.forEach { button ->
			val buttonView = when(button.type) {
				BUTTON -> createButtonView(button)
			}
			addView(buttonView)
		}
	}

	private fun createButtonView(button: FormButton): Button {
		return Button(requireContext()).apply {
			hint = button.caption
			setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
			setOnClickListener { viewModel.onButtonClick(button.formAction) }
		}
	}

	private fun createUserView(viewOnTopId: Int): View {
		val recyclerView = RecyclerView(requireContext())
		val layoutParams = ConstraintLayout.LayoutParams(0, 0).apply {
			topToBottom = viewOnTopId
			bottomToBottom = PARENT_ID
			startToStart = PARENT_ID
			endToEnd = PARENT_ID
			topMargin = convertDpToPx(requireContext(), 8)
		}
		recyclerView.layoutParams = layoutParams
		recyclerView.layoutManager = LinearLayoutManager(
			requireContext(),
			LinearLayoutManager.VERTICAL,
			false
		)
		userInfoAdapter = UserInfoAdapter()
		recyclerView.adapter = userInfoAdapter
		return recyclerView
	}

	@SuppressLint("NotifyDataSetChanged")
	private fun showUserInfo(user: User) {
		userInfoAdapter.data = user.getFormattedUserInfo()
		userInfoAdapter.notifyDataSetChanged()
	}

	private fun User.getFormattedUserInfo() = listOf(
		fullName,
		String.format(resources.getString(R.string.position), position),
		String.format(resources.getString(R.string.work_hours_in_month), workHoursInMonth),
		String.format(resources.getString(R.string.worked_out_hours), workedOutHours)
	)
}