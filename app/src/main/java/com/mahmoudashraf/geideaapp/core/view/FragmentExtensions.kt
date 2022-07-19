package com.mahmoudashraf.geideaapp.core.view

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.replaceFragment(fragment: Fragment, @IdRes frame: Int) {
    supportFragmentManager
        .beginTransaction()
        .replace(frame, fragment, fragment::class.java.simpleName)
        .commit()
}
fun Fragment.replaceFragment(fragment: Fragment, @IdRes frame: Int) {
    requireActivity().supportFragmentManager
        .beginTransaction()
        .replace(frame, fragment)
        .addToBackStack(fragment::class.simpleName)
        .commit()
}

