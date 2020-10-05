package com.example.contentlist.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.contentlist.R
import com.example.contentlist.data.network.Routes
import com.example.contentlist.data.repo.ContentListRepo
import com.example.contentlist.data.repo.ContentListRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

	@Singleton
	@Provides
	fun provideRepo(
		routes: Routes
	): ContentListRepo = ContentListRepoImpl(routes)

	@Singleton
	@Provides
	fun provideGlideInstance(
		@ApplicationContext context: Context
	) = Glide.with(context).setDefaultRequestOptions(
		RequestOptions()
			.placeholder(R.drawable.placeholder_for_missing_posters)
			.error(R.drawable.placeholder_for_missing_posters)
	)
}
