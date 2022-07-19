package com.mahmoudashraf.geideaapp.presentation.userlist.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mahmoudashraf.geideaapp.R
import com.mahmoudashraf.geideaapp.core.view.viewBinding
import com.mahmoudashraf.geideaapp.data.entity.User
import com.mahmoudashraf.geideaapp.databinding.FragmentUsersListBinding
import com.mahmoudashraf.geideaapp.presentation.userlist.view.adapter.UsersAdapter
import com.mahmoudashraf.geideaapp.presentation.userlist.viewmodel.UsersListScreenState
import com.mahmoudashraf.geideaapp.presentation.userlist.viewmodel.UsersListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersListFragment : Fragment(R.layout.fragment_users_list) {

    private val binding by viewBinding(FragmentUsersListBinding::bind)
    private val viewModel : UsersListViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.screenState.observe(viewLifecycleOwner, ::onScreenStateChange)
    }

    private fun onScreenStateChange(state: UsersListScreenState) {
        when (state) {
            is UsersListScreenState.Loading -> showLoading()
            is UsersListScreenState.Success -> handleSuccessState(state.users)
            is UsersListScreenState.Error -> handleError(state.message)
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

    private fun handleSuccessState(users : List<User>) {
        hideLoading()
        binding.rvUsers.adapter = UsersAdapter(users,::onUserClicked)
    }
    private fun onUserClicked(user: User) {

    }


}