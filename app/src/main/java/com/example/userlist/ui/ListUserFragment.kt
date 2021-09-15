package com.example.userlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userlist.R
import com.example.userlist.adapter.UserListAdapter
import com.example.userlist.databinding.FragmentListUserBinding
import com.example.userlist.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListUserFragment : Fragment() {
    private lateinit var dataBinding: FragmentListUserBinding
    private lateinit var userListAdapter: UserListAdapter
    private val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_list_user, container, false)
        dataBinding.lifecycleOwner = this
        dataBinding.viewmodel = userViewModel

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialView()
        fetchDataFromServer()
        initializeListener()

    }

    private fun initializeListener() {
        userViewModel.userList.observe(viewLifecycleOwner) {
            if (dataBinding.swipeRefreshHome.isRefreshing) {
                dataBinding.swipeRefreshHome.isRefreshing = false
            }
            userListAdapter.submitList(it)

        }
        userViewModel.errorMessage.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            if (dataBinding.swipeRefreshHome.isRefreshing) {
                dataBinding.swipeRefreshHome.isRefreshing = false
            }
        }
        dataBinding.swipeRefreshHome.setOnRefreshListener {
            onSwipeRefresh()
        }

    }

    private fun onSwipeRefresh() {
        userViewModel.getUserList(requireContext(), false)
        ádasdasdsad

        // checkin nhanh khoa 3

        // checkin nhanh khoa 4
        // checkin nhanh khoa 2
        // Khanh dep trai
        // Khanh dep trai 2
    }

    private fun fetchDataFromServer() {
        userViewModel.getUserList(requireContext(),true)
    }

    private fun initialView() {
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        dataBinding.rcvUserList.layoutManager =linearLayoutManager
        userListAdapter = UserListAdapter {
            var action  = ListUserFragmentDirections.actionListUserFragmentToUserDetailFragment(it)
            view?.findNavController()?.navigate(action)
        }
        dataBinding.rcvUserList.adapter = userListAdapter

    }

    companion object {

    }
}