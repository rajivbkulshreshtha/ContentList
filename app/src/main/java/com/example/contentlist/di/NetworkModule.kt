package com.example.contentlist.di

import android.content.Context
import com.example.contentlist.BuildConfig
import com.example.contentlist.data.network.CacheInterceptor
import com.example.contentlist.data.network.Routes
import com.example.contentlist.other.Constants.BASE_URL
import com.example.contentlist.other.Constants.CACHE_DIR_NAME
import com.example.contentlist.other.Constants.CACHE_DIR_SIZE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import zerobranch.androidremotedebugger.logging.NetLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

	@Singleton
	@Provides
	fun provideCache(
		@ApplicationContext app: Context
	): Cache {
		val directory =
			File(app.cacheDir.toString() + File.separator + CACHE_DIR_NAME)
		if (!directory.exists())
			directory.mkdirs()
		return Cache(directory, CACHE_DIR_SIZE)
	}


	@Singleton
	@Provides
	fun provideOkHttp(cache: Cache): OkHttpClient {
		val builder = OkHttpClient.Builder()
			.cache(cache)
			.readTimeout(60, TimeUnit.SECONDS)
			.connectTimeout(60, TimeUnit.SECONDS)
			.retryOnConnectionFailure(true)
			.addInterceptor(CacheInterceptor())
		if (BuildConfig.DEBUG) {
			builder.addInterceptor(NetLoggingInterceptor())
		}
		return builder.build()
	}


	@Singleton
	@Provides
	fun provideRoutes(
		okHttpClient: OkHttpClient
	) = Retrofit.Builder()
		.client(okHttpClient)
		.baseUrl(BASE_URL)
		.addConverterFactory(GsonConverterFactory.create())
		.build()
		.create(Routes::class.java)

}
