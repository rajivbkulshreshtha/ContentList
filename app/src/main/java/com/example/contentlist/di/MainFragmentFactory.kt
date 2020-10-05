package com.example.contentlist.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.contentlist.ui.view.adapter.ContentAdapter
import com.example.contentlist.ui.view.fragment.ContentFragment
import javax.inject.Inject

class MainFragmentFactory @Inject constructor(
	private val contentAdapter: ContentAdapter
) : FragmentFactory() {

	companion object {
		public const val TAG = "MainFragmentFactory"
	}

	override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
		return when (className) {
			ContentFragment::class.java.name -> ContentFragment(contentAdapter)
			else -> super.instantiate(classLoader, className)
		}
	}
}
