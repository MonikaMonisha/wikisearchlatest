package com.example.daytodayfinalapplication.view.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewConfiguration
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daytodayfinalapplication.R
import com.example.daytodayfinalapplication.domain.entity.SearchResult
import com.example.daytodayfinalapplication.framework.di.ViewModelFactory
import com.example.daytodayfinalapplication.utils.*
import com.example.daytodayfinalapplication.utils.onTextChanged
import com.example.daytodayfinalapplication.view.adapters.SearchListAdapter
import com.example.daytodayfinalapplication.view.viewmodel.SearchViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_wiki_search.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var listAdapter: SearchListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var openWikipediaPageHandler: OpenWikipediaPageHandler

    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wiki_search)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(SearchViewModel::class.java)

        setupListView()
        hideKeyboardOnScroll()
        observeState()
        observeQueryText()
    }

    private fun observeQueryText() {
        query_input.onTextChanged {
            clear_query.isVisible = it.isNotEmpty()
            if (it.isBlank()) {
                listAdapter.submitList(emptyList())
            }
            viewModel.search(it.toString())
        }
    }

    private fun observeState() {
        viewModel.searchResult().observe(this, Observer { state ->
            when (state) {
                is SearchState.Error -> {
                    Toast.makeText(applicationContext, state.message, Toast.LENGTH_SHORT)
                        .setGravity(Gravity.CENTER, 0, 0)
                }
                is SearchState.Success -> {
                    results.visibility = View.VISIBLE
                    listAdapter.submitList(state.result)

                }
            }
        })
    }

    private fun setupListView() {
        clear_query.setOnClickListener {
            query_input.setText("")
        }

        listAdapter.clickListener = searchItemListener
        results.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun hideKeyboardOnScroll() {
        val touchSlop = ViewConfiguration.get(this).scaledTouchSlop
        var totalDy = 0
        results.onScroll { _, dy ->
            if (dy > 0) {
                totalDy += dy
                if (totalDy >= touchSlop) {
                    totalDy = 0

                    val inputMethodManager = getSystemService<InputMethodManager>()
                    inputMethodManager?.hideSoftInputFromWindow(
                        query_input.windowToken,
                        HIDE_NOT_ALWAYS
                    )
                }
            }
        }
    }

    private val searchItemListener = object : SearchItemListener {

        override fun onSearchResultClicked(searchResult: SearchResult) {
            Log.e("testresult", searchResult.description?:"")
            openWikipediaPageHandler(searchResult)
        }

    }

}
