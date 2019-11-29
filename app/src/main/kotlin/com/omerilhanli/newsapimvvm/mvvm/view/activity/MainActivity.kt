package com.omerilhanli.newsapimvvm.mvvm.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.app.DatePickerDialog
import androidx.databinding.DataBindingUtil
import com.omerilhanli.newsapimvvm.R
import com.omerilhanli.newsapimvvm.asistant.URL_GITHUB
import com.omerilhanli.newsapimvvm.asistant.callback.DateSelection
import com.omerilhanli.newsapimvvm.asistant.extension.configure
import com.omerilhanli.newsapimvvm.asistant.extension.inject
import com.omerilhanli.newsapimvvm.asistant.extension.openWebTabBrowser
import com.omerilhanli.newsapimvvm.asistant.extension.openDatePickerDialog
import com.omerilhanli.newsapimvvm.databinding.ActivityMainBinding
import com.omerilhanli.newsapimvvm.mvvm.model.entity.DeclarativeDate
import com.omerilhanli.newsapimvvm.mvvm.view.fragment.NewsFragment
import com.omerilhanli.newsapimvvm.mvvm.viewmodel.ViewModelMain
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragment: NewsFragment

    @Inject
    lateinit var viewModel: ViewModelMain

    private val dateSetListener: DatePickerDialog.OnDateSetListener =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->

            val date = viewModel.prepareAndGetSelectedDate(year, monthOfYear, dayOfMonth)

            sendDateToAttachedFragment(date)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        supportActionBar?.configure()

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        binding.fragment = fragment
        binding.fragmentManager = supportFragmentManager
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_news, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_filter -> openDatePicker(viewModel.getDeclarativeDate())
            android.R.id.home -> this.openWebTabBrowser(URL_GITHUB)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun openDatePicker(declerativeDate: DeclarativeDate) {
        openDatePickerDialog(declerativeDate, dateSetListener).show()
    }

    private fun sendDateToAttachedFragment(date: Date?) {
        (fragment as DateSelection).onDateSelect(date)
    }
}
