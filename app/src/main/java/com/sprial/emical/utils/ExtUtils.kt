package com.sprial.emical.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

class ExtUtils {
}

fun Double?.formatCurrency(): String {  //৳
    return "${this.roundDouble() ?: 0}"
    /*val COUNTRY = "BD"
    val LANGUAGE = "en"
    val format = NumberFormat.getCurrencyInstance(Locale(LANGUAGE, COUNTRY))
    format.maximumFractionDigits = 2
    format.minimumFractionDigits = 2

    return format.format(this)*/
}

fun Int?.formatCurrency(): String {  //৳
    return "${this?.formatInt() ?: 0}"
}

fun Int?.formatInt(): String {
    this?.let {
        val nf: NumberFormat = NumberFormat.getNumberInstance(Locale.US)
        val formatter = nf as DecimalFormat
        formatter.applyPattern("##,##,##,##,##0")
        return formatter.format(this)
    }
    return "0.00"
}

fun Double?.roundDouble(): String {
    this?.let {
        val nf: NumberFormat = NumberFormat.getNumberInstance(Locale.US)
        val formatter = nf as DecimalFormat
        formatter.applyPattern("##,##,##,##,##0.00")
        //   val formatter = DecimalFormat("##,##,##,##,##0.00")
        return formatter.format(this)
    }
    return "0.00"
}

fun Double?.toRoundTwoDigit(): Double {
    this?.let {
        val nf: NumberFormat = NumberFormat.getNumberInstance(Locale.US)
        val formatter = nf as DecimalFormat
        formatter.applyPattern("##########0.00")
        return formatter.format(this).toDouble()
    }
    return 0.00
}

fun Float?.toRoundTwoDigit(): Float {
    this?.let {
        val nf: NumberFormat = NumberFormat.getNumberInstance(Locale.US)
        val formatter = nf as DecimalFormat
        formatter.applyPattern("##########0.00")
        return formatter.format(this).toFloat()
    }
    return 0.00f
}
