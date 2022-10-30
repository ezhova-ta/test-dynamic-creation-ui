package com.example.testdynamiccreationui.presentation.utils

import android.content.Context

fun convertDpToPx(context: Context, dp: Int) =
	(dp * context.resources.displayMetrics.density).toInt()