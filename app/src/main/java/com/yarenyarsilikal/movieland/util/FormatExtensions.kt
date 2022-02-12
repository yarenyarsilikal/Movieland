package com.yarenyarsilikal.movieland.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat


/**
 * Created by yarenyarsilikal on 11.02.2022.
 */

@SuppressLint("SimpleDateFormat")
fun String.formatDate(format : String): String {
    val initDate = SimpleDateFormat("yyyy-mm-dd").parse(this)
    val formatter = SimpleDateFormat(format)
    return formatter.format(initDate)
}