package com.example.testdynamiccreationui.presentation.adapters

import android.util.TypedValue
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.example.testdynamiccreationui.presentation.utils.convertDpToPx

class UserInfoAdapter : RecyclerView.Adapter<UserInfoAdapter.ViewHolder>() {
	var data: List<String> = emptyList()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = TextView(parent.context)
		view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
		return ViewHolder(view)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.textView.text = data[position]
	}

	override fun getItemCount(): Int = data.size

	class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}