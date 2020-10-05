package com.example.contentlist.data.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.contentlist.data.model.Content
import kotlinx.coroutines.flow.Flow


interface ContentListRepo {
	fun getContents(): LiveData<PagingData<Content>>
}
