package com.yarenyarsilikal.movieland.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment


/**
 * Created by yarenyarsilikal on 12.02.2022.
 */

fun Fragment.navigate(resId : Int){
    NavHostFragment.findNavController(this)
        .navigate(resId)
}

fun Fragment.navigate(resId : Int, args : Bundle?){
    NavHostFragment.findNavController(this)
        .navigate(resId, args)
}