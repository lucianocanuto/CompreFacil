package com.lucianocanuto.comprefacil.UTIL

import android.graphics.Color
import androidx.core.text.buildSpannedString
import androidx.core.text.color

fun CompreFacilLogo() = buildSpannedString {
    color(Color.parseColor("#DAA520")) {append("Compre \uD83D\uDECD\uFE0F \n ")}
    color(Color.parseColor("#DAA520")) {append("  Fácil ✨")}
}