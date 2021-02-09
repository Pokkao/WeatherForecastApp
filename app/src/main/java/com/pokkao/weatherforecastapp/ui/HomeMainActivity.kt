package com.pokkao.weatherforecastapp.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pokkao.weatherforecastapp.R
import com.pokkao.weatherforecastapp.ui.main.WeatherForecastMainFragment
import kotlinx.android.synthetic.main.activity_main.*

class HomeMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragmentWithBackStackContentContainer(
            WeatherForecastMainFragment.newInstance(),
            WeatherForecastMainFragment.TAG_NAME
        )
    }

    private fun replaceFragmentWithBackStackContentContainer(
            fragment: Fragment,
            tag: String? = null
    ) {
        supportFragmentManager.beginTransaction()
            .replace(fmWeatherForecast.id, fragment, tag)
            .commit()
    }

    fun addFragmentWithBackStackContentContainer(
        fragment: Fragment,
        tag: String? = null,
        backStackName: String? = null
    ) {
        supportFragmentManager.beginTransaction()
            .add(fmWeatherForecast.id, fragment, tag)
            .addToBackStack(backStackName)
            .commit()
    }

    fun setupClickOutsideKeyboard(view: View) {
        if (view !is EditText) {
            view.setOnTouchListener { _, _ ->
                hideKeyboardCurrentFocus()
                false
            }
        }

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupClickOutsideKeyboard(innerView)
            }
        }
    }

    private fun hideKeyboardCurrentFocus() {
        val view = this.currentFocus
        view?.findFocus()?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

}
