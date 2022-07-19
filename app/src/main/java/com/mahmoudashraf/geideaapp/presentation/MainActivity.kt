package com.mahmoudashraf.geideaapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mahmoudashraf.geideaapp.R
import com.mahmoudashraf.geideaapp.core.view.replaceFragment
import com.mahmoudashraf.geideaapp.presentation.userlist.view.fragment.UsersListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState==null)
            replaceFragment(UsersListFragment(),R.id.fl_app_screens)
    }
}