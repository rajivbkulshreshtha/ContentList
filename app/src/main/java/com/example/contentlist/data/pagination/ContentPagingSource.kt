package com.example.contentlist.data.pagination

import androidx.paging.PagingSource
import com.example.contentlist.data.model.Content
import com.example.contentlist.data.network.Routes
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class ContentPagingSource(
	private val routes: Routes
) : PagingSource<Int, Content>() {

	companion object {
		const val TAG = "ContentPagingSource"
	}

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Content> {
		val position = params.key ?: STARTING_PAGE_INDEX

		return try {
			val pageResponse = routes.pageResponse(params.key ?: STARTING_PAGE_INDEX)
			val contents = pageResponse.page?.contentItems?.content
			LoadResult.Page(
				data = contents ?: mutableListOf(),
				prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
				nextKey = if (contents.isNullOrEmpty()) null else position + 1
			)
		} catch (exception: IOException) {
			exception.printStackTrace()
			LoadResult.Error(exception)
		} catch (exception: HttpException) {
			exception.printStackTrace()
			LoadResult.Error(exception)
		}
	}
}
