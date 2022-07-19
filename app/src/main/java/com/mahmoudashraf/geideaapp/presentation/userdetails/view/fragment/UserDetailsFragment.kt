package com.mahmoudashraf.geideaapp.presentation.userdetails.view.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.mahmoudashraf.geideaapp.R
import com.mahmoudashraf.geideaapp.core.view.viewBinding
import com.mahmoudashraf.geideaapp.data.entity.User
import com.mahmoudashraf.geideaapp.databinding.FragmentUserDetailsBinding
import com.mahmoudashraf.geideaapp.presentation.userdetails.view.services.CountDownTimerService
import com.mahmoudashraf.geideaapp.presentation.userdetails.viewmodel.UserDetailsViewModel
import com.mahmoudashraf.geideaapp.presentation.userdetails.viewmodel.UsersDetailsScreenState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {
    private val userId: Int by lazy { arguments?.getInt("userId") ?: -1 }
    private val binding by viewBinding(FragmentUserDetailsBinding::bind)
    private val viewModel: UserDetailsViewModel by viewModels()
    private val counterBroadCast = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, intent: Intent?) {
            val counter = intent?.getIntExtra("countdown", 0) ?: 0
            viewModel.counter = counter
            binding.tvTimer.text = viewModel.counter.toString().plus(" Sec.")
            val backRequired = intent?.getBooleanExtra("finish", false) ?: false
            viewModel.backRequired = backRequired
            if (viewModel.backRequired) activity?.onBackPressed()
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.screenState.observe(viewLifecycleOwner, ::onScreenStateChange)
        viewModel.getUsers(userId)
    }

    override fun onResume() {
        super.onResume()
        binding.tvTimer.text = viewModel.counter.toString().plus(" Sec.")
        if (viewModel.backRequired) activity?.onBackPressed()
        registerService()
    }


    override fun onDestroy() {
        super.onDestroy()
        activity?.unregisterReceiver(counterBroadCast)
        activity?.stopService(Intent(context, CountDownTimerService::class.java))
    }


    private fun onScreenStateChange(state: UsersDetailsScreenState) {
        when (state) {
            is UsersDetailsScreenState.Loading -> showLoading()
            is UsersDetailsScreenState.Success -> handleSuccessState(state.user)
            is UsersDetailsScreenState.Error -> handleError(state.message)
        }
    }

    private fun handleError(message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoading() {
        binding.progressBar.isVisible = true
    }

    private fun hideLoading() {
        binding.progressBar.isVisible = false
    }

    private fun handleSuccessState(user: User) {
        hideLoading()
        with(binding) {
            tvUserId.text = user.id.toString()
            tvFirstName.text = user.firstName
            tvLastName.text = user.lastName
            tvEmail.text = user.email
            tvTimer.text = "5 Sec."
            activity?.startService(Intent(context, CountDownTimerService::class.java))
        }
    }

    private fun registerService() {
        val filter = IntentFilter("com.geidea.timer_receiver")
        activity?.registerReceiver(counterBroadCast, filter)
    }

}
