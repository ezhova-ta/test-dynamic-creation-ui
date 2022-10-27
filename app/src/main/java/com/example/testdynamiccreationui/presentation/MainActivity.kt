package com.example.testdynamiccreationui.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.testdynamiccreationui.R
import com.example.testdynamiccreationui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val binding = ActivityMainBinding.inflate(layoutInflater)
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