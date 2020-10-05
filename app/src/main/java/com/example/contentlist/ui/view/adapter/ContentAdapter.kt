package com.example.contentlist.ui.view.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.contentlist.R
import com.example.contentlist.data.model.Content
import com.example.contentlist.databinding.AdapterContentBinding
import com.example.imgursearch.other.resIdByName
import javax.inject.Inject

class ContentAdapter @Inject constructor(
	private val glide: RequestManager
) : PagingDataAdapter<Content, ContentAdapter.ViewHolder>(
	CONTENT_COMPARATOR
) {


	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val binding =
			AdapterContentBinding.inflate(
				LayoutInflater.from(parent.context), parent, false
			)

		return ViewHolder(
			binding,
			glide
		)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val currentItem = getItem(position)

		if (currentItem != null) {
			holder.bind(currentItem)
		}
	}

	class ViewHolder(
		private val binding: AdapterContentBinding,
		private val glide: RequestManager
	) :
		RecyclerView.ViewHolder(binding.root) {

		fun bind(content: Content?) {

			binding.apply {

				content?.let {
					val context = itemView.context
					val name = content.posterImage?.removeSuffix(".jpg")
					val resourceId = context.resIdByName(name, "drawable")

					try {
						if (resourceId != 0) {
							glide.load(ContextCompat.getDrawable(context, resourceId))
								.centerCrop()
								.placeholder(R.drawable.placeholder_for_missing_posters)
								.into(imageView)

							textView.text = content.name
						}
					} catch (e: Resources.NotFoundException) {
						e.printStackTrace()
					}

				}
			}
		}
	}

	companion object {
		const val TAG = "ContentAdapter"
		private val CONTENT_COMPARATOR = object : DiffUtil.ItemCallback<Content>() {
			override fun areItemsTheSame(oldItem: Content, newItem: Content) =
				oldItem.id == newItem.id

			override fun areContentsTheSame(oldItem: Content, newItem: Content) =
				oldItem == newItem
		}
	}
}
