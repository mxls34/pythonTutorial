package com.example.pythontutorial

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

fun View.applySystemBarsPadding(includeBottom: Boolean = true) {
    val padLeft = paddingLeft
    val padTop = paddingTop
    val padRight = paddingRight
    val padBottom = paddingBottom

    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        val bars = insets.getInsets(
            WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
        )
        v.setPadding(
            padLeft + bars.left,
            padTop + bars.top,
            padRight + bars.right,
            padBottom + if (includeBottom) bars.bottom else 0
        )
        insets
    }
}
