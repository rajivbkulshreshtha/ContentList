package com.example.contentlist.ui.view.fragment

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.contentlist.R
import com.example.contentlist.databinding.FragmentContentBinding
import com.example.contentlist.other.Constants.GRID_SPAN_COUNT_LANDSCAPE
import com.example.contentlist.other.Constants.GRID_SPAN_COUNT_PORTRAIT
import com.example.contentlist.ui.view.adapter.ContentAdapter
import com.example.contentlist.ui.viewmodel.ContentViewModel
import com.example.imgursearch.other.changeToolbarFont
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContentFragment(
	val contentAdapter: ContentAdapter
) : Fragment() {

	private var _binding: FragmentContentBinding? = null
	private val binding get() = _binding!!

	private val viewModel: ContentViewModel by navGraphViewModels(R.id.nav_graph) {
		defaultViewModelProviderFactory
	}

	companion object {
		public const val TAG = "ContentFragment"
	}


	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentContentBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setHasOptionsMenu(true)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		try {
			(activity as AppCompatActivity?)?.let {
				it.setSupportActionBar(binding.toolbar)
				it.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_action_back)
				it.supportActionBar?.setHomeButtonEnabled(true)
			}
			binding.toolbar.changeToolbarFont()
		} catch (e: Exception) {
			Log.d(TAG, "onViewCreated: error => " + e.localizedMessage)
		}

		setupRecyclerView()
		subscribeToObservers()
	}

	private fun setupRecyclerView() {
		binding.recyclerView.apply {
			setHasFixedSize(true)
			adapter = contentAdapter

			var GRID_SPAN_COUNT = GRID_SPAN_COUNT_PORTRAIT
			if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
				GRID_SPAN_COUNT = GRID_SPAN_COUNT_LANDSCAPE
			}

			layoutManager = GridLayoutManager(requireContext(), GRID_SPAN_COUNT)
		}
	}


	private fun subscribeToObservers() {

		viewModel.contents.observe(viewLifecycleOwner, Observer {
			it?.let {
				contentAdapter.submitData(viewLifecycleOwner.lifecycle, it)
			}
		})

	}


	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		inflater.inflate(R.menu.menu, menu)
		val searchViewItem: MenuItem = menu.findItem(R.id.app_bar_search)
		val searchView: SearchView = searchViewItem.actionView as SearchView
		val searchIconView: ImageView =
			searchView.findViewById<View>(androidx.appcompat.R.id.search_close_btn) as ImageView
		searchIconView.setImageResource(R.drawable.ic_action_search_cancel)
		super.onCreateOptionsMenu(menu, inflater)
	}


	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}

}
