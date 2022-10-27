package com.example.testdynamiccreationui.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.testdynamiccreationui.R
import com.example.testdynamiccreationui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private var _binding: ActivityMainBinding? = null
	private val binding get() = _binding!!

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		_binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		if(savedInstanceState == null) {
			showMainFragment()
		}
	}

	private fun showMainFragment() {
		supportFragmentManager.commit {
			setReorderingAllowed(true)
			add<MainFragment>(R.id.fragmentContainerView)
		}
	}
}