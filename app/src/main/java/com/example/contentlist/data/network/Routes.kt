package com.example.contentlist.data.network

import com.example.contentlist.data.model.PageResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Routes {
	@GET("data/{page_no}")
	suspend fun pageResponse(@Path("page_no") pageNo: Int): PageResponse
}
