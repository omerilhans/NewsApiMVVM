package com.omerilhanli.newsapimvvm.mvvm.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.omerilhanli.newsapimvvm.R
import com.omerilhanli.newsapimvvm.databinding.FragmentNewsBinding
import com.omerilhanli.newsapimvvm.mvvm.view.adapter.ArticleAdapter
import com.omerilhanli.newsapimvvm.mvvm.viewmodel.ViewModelNews
import androidx.lifecycle.Observer
import com.omerilhanli.newsapimvvm.asistant.TEXT_DEFAULT_SEARCH
import com.omerilhanli.newsapimvvm.asistant.TEXT_ERR_NO_NETWORK
import com.omerilhanli.newsapimvvm.asistant.callback.DateSelection
import com.omerilhanli.newsapimvvm.asistant.callback.ItemClickListener
import com.omerilhanli.newsapimvvm.asistant.extension.*
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsFragment
@Inject constructor(
    var viewModel: ViewModelNews,
    var adapter: ArticleAdapter
) : Fragment(), DateSelection {

    private lateinit var binding: FragmentNewsBinding

    private val itemClickListener = object :
        ItemClickListener {
        override fun click(url: String) {
            if (viewModel.isNetworkStatus?.invoke() == true)
                context?.openWebTabBrowser(url) // just open
            else
                handleError(TEXT_ERR_NO_NETWORK)
        }
    }

    private val queryTextChangeListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            viewModel.passQueryText(query.trim()) // just pass
            return false
        }

        override fun onQueryTextChange(p0: String?): Boolean {
            return false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_news, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepare()
        search()
        subscribe()
    }

    override fun onDateSelect(date: Date?) {
        viewModel.passSelectedDate(date) // just pass
    }

    private fun prepare() {
        binding.lifecycleOwner = this

        binding.listVisibility = viewModel.visibilityList
        binding.progressVisibility = viewModel.visibilityProgress
        binding.adapter = adapter
        binding.listener = itemClickListener

        //
        binding.searchView.setOnQueryTextListener(queryTextChangeListener)
        binding.searchView.isIconifiedByDefault = false

        viewModel.isNetworkStatus = ::handleNetworkConnection // just pass function
    }

    private fun search() {
        binding.searchView.setQuery(TEXT_DEFAULT_SEARCH, true)
    }

    private fun subscribe() {
        viewModel
            .liveDataNews
            .observe(activity!!, Observer { news ->
                binding.articles = news?.articles.sortForDate()
                binding.searchView.hideKeyboard()
            })

        viewModel
            .liveDataError
            .observe(activity!!, Observer { msg ->
                handleError(msg)
            })
    }

    private fun handleError(msg: String) {
        context!!.showPopupFor(msg)
        binding.tvEmptyMessage.text = msg
    }

    private fun handleNetworkConnection(): Boolean {
        return context?.hasNetworkConnection()!!
    }
}