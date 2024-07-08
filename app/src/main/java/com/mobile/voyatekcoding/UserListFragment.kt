package com.mobile.voyatekcoding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mobile.domain.model.UserResponseData
import com.mobile.voyatekcoding.databinding.FragmentUserListBinding
import com.mobile.voyatekcoding.ui.UserViewModel
import com.mobile.voyatekcoding.ui.adapter.UserDetailClick
import com.mobile.voyatekcoding.ui.adapter.UserListAdapter
import com.mobile.voyatekcoding.util.CustomProgressDialog

class UserListFragment : Fragment(), UserDetailClick {

    private var _binding: FragmentUserListBinding? = null
    private val viewModel: UserViewModel by viewModels()
    private val userListAdapter : UserListAdapter by lazy { UserListAdapter(this@UserListFragment) }
    private val progressDialog by lazy { CustomProgressDialog(requireActivity()) }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.usersRecyclerview.apply {
            adapter = userListAdapter
        }

        observeUsersFetch()
    }

    private fun observeUsersFetch() {
        viewModel.fetchAllUsersRequest.observe(requireActivity()) { result ->
            result?.getContentIfNotHandled()?.let { value ->
                when {
                    value.isSuccess() -> {
                        value.data?.let { data ->
                            when {
                                data.isEmpty() -> {
                                    binding.noUser.visibility= VISIBLE
                                }
                                else -> {
                                    binding.noUser.visibility= GONE
                                    userListAdapter.submitList(data)
                                }
                            }
                        }
                        progressDialog.stop()
                    }

                    value.isLoading() -> {
                        progressDialog.start(title = "fetching ...")
                    }

                    value.isError() -> {
                        progressDialog.stop()
                    }

                    else -> {}
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun clickOnItem(data: UserResponseData) {
        findNavController().navigate()
    }
}