package com.pokkao.weatherforecastapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.pokkao.weatherforecastapp.BuildConfig
import com.pokkao.weatherforecastapp.R
import com.pokkao.weatherforecastapp.ui.HomeMainActivity
import kotlinx.android.synthetic.main.layout_splash_screen.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.layout_splash_screen)

        tvVersionApp.text = "${getString(R.string.version_name)} ${BuildConfig.VERSION_NAME}"

        Handler(Looper.getMainLooper()).postDelayed({
            startMainScreen()
        }, 1000)

    }

    private fun startMainScreen() {
        finish()
        val intent = Intent(this, HomeMainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

}
