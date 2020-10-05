package com.example.contentlist.data.repo


import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.*
import com.example.contentlist.data.model.Content
import com.example.contentlist.data.network.Routes
import com.example.contentlist.data.pagination.ContentPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ContentListRepoImpl @Inject constructor(var routes: Routes) : ContentListRepo {

	companion object {
		public const val TAG = "ContentListRepoImpl"

	}

	override fun getContents() = Pager(
		config = PagingConfig(
			pageSize = 10,
			maxSize = 54,
			enablePlaceholders = false
		),
		pagingSourceFactory = { ContentPagingSource(routes) }
	).liveData

}
