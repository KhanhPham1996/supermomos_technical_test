package com.example.userlist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.example.userlist.R
import com.example.userlist.databinding.FragmentListUserBinding
import com.example.userlist.databinding.FragmentUserDetailBinding
import com.example.userlist.model.User
import com.example.userlist.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : Fragment() {
    private lateinit var dataBinding: FragmentUserDetailBinding
    private val args : UserDetailFragmentArgs by navArgs()
    private var userLogin : String = ""
   private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userLogin = args.userLogin
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_detail, container, false)
        dataBinding.lifecycleOwner = this
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.viewModel = userViewModel
        fetchUserDetail()
        initializeListener()
    }

    private fun initializeListener() {
        userViewModel.userDetail.observe(viewLifecycleOwner){
            if (dataBinding.swipeRefreshDetail.isRefreshing) {
                dataBinding.swipeRefreshDetail.isRefreshing = false
            }
           it?.let {
               dataBinding.userDeail = it
           }
        }
        userViewModel.errorMessage.observe(viewLifecycleOwner){
            if (dataBinding.swipeRefreshDetail.isRefreshing) {
                dataBinding.swipeRefreshDetail.isRefreshing = false
            }
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        dataBinding.imgBack.setOnClickListener {
            requireActivity()?.onBackPressed()
        }
        dataBinding.swipeRefreshDetail.setOnRefreshListener {
            onSwipeRefresh()
        }
    }

    private fun onSwipeRefresh() {
        userViewModel.getUserDetail(requireContext(),userLogin,false)
    }

    private fun fetchUserDetail() {
        userViewModel.getUserDetail(requireContext(),userLogin,true)
    }


}