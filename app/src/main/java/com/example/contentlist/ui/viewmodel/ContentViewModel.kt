package com.example.contentlist.ui.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.contentlist.data.repo.ContentListRepo


class ContentViewModel @ViewModelInject constructor(
	@Assisted private val savedStateHandle: SavedStateHandle,
	val repo: ContentListRepo
) : ViewModel(), LifecycleObserver {

	companion object {
		public const val TAG = "ContentViewModel"
		const val SEARCH_DELAY_MS = 500L
		const val MIN_QUERY_LENGTH = 3
	}

	val contents = repo.getContents().cachedIn(viewModelScope)

}
