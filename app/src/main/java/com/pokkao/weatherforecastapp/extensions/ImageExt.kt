package com.pokkao.weatherforecastapp.extensions

import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat

fun ImageView.setImage(icDrawable: Int) {
    setImageDrawable(ResourcesCompat.getDrawable(resources, icDrawable, null))
}