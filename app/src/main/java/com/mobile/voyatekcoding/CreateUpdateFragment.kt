package com.mobile.voyatekcoding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mobile.domain.model.UserResponseData
import com.mobile.voyatekcoding.databinding.FragmentCreateUserBinding
import com.mobile.voyatekcoding.databinding.FragmentSecondBinding
import com.mobile.voyatekcoding.ui.UserViewModel
import com.mobile.voyatekcoding.util.CustomProgressDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateUpdateFragment : Fragment() {

    private var _binding: FragmentCreateUserBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: UserViewModel by viewModels()

    private val progressDialog by lazy { CustomProgressDialog(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCreateUserBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (args.userDetail.id != null) {
            binding.next.text = getString(R.string.update)
            binding.deleteImg.visibility = VISIBLE
            binding.firstName.setText(args.userDetail.firstName)
            binding.lastName.setText(args.userDetail.lastName)
            binding.addressInput.setText(args.userDetail.address)
            binding.gender.setText(args.userDetail.gender)
            binding.email.setText(args.userDetail.email)
            binding.phone.setText(args.userDetail.phoneNumber)
        } else {
            binding.next.text = getString(R.string.create_user)
        }

        binding.next.setOnClickListener {
            val firstName = binding.firstName.text.toString().trim()
            val lastName = binding.lastName.text.toString().trim()
            val phoneNumber = binding.phone.text.toString().trim()
            val email = binding.email.text.toString().trim()
            val address = binding.addressInput.text.toString().trim()
            val gender = binding.gender.text.toString().trim()

            when {
                firstName.isEmpty() -> {
                    binding.selectFirstNameInput.isErrorEnabled = true
                    binding.selectFirstNameInput.error = "First name is required"
                }

                lastName.isEmpty() -> {
                    binding.selectLastNameInput.isErrorEnabled = true
                    binding.selectLastNameInput.error = "Last name is required"
                }

                phoneNumber.isEmpty() -> {
                    binding.selectPhoneInput.isErrorEnabled = true
                    binding.selectPhoneInput.error = "Phone number is required"
                }

                phoneNumber.length < 11 -> {
                    binding.selectPhoneInput.isErrorEnabled = true
                    binding.selectPhoneInput.error = "Phone number length should be 11 "
                }

                email.isEmpty() -> {
                    binding.selectEmailInput.isErrorEnabled = true
                    binding.selectEmailInput.error = "Email is required"
                }

                address.isEmpty() -> {
                    binding.selectAddressInput.isErrorEnabled = true
                    binding.selectAddressInput.error = "Address is required"
                }

                gender.isEmpty() -> {
                    binding.selectGenderInput.isErrorEnabled = true
                    binding.selectGenderInput.error = "Gender is required"
                }

                else -> {
                    if (args.userDetail.id != null) {
                        viewModel.updateUser(
                            UserResponseData(
                                firstName = firstName,
                                lastName = lastName,
                                email = email,
                                phoneNumber = phoneNumber,
                                gender = gender,
                                address = address,
                                id = args.userDetail.id
                            )
                        )

                        updateUserObserver()
                    } else {
                        viewModel.createUser(
                            UserResponseData(
                                firstName = firstName,
                                lastName = lastName,
                                email = email,
                                phoneNumber = phoneNumber,
                                gender = gender,
                                address = address,
                                id = args.userDetail.id
                            )
                        )

                        createUserObserver()
                    }
                }
            }
        }

        binding.deleteImg.setOnClickListener {
            viewModel.deleteUser(id = args.userDetail.id ?: "")
            observeDelete()
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun createUserObserver() {
        viewModel.createUserRequest.observe(requireActivity()) { result ->
            result?.getContentIfNotHandled()?.let { value ->
                when {
                    value.isSuccess() -> {
                        value.data?.let { data ->
                           Toast.makeText(requireContext(), "User created successfully", Toast.LENGTH_SHORT).show()
                            resetFields()
                        }
                        progressDialog.stop()
                    }

                    value.isLoading() -> {
                        progressDialog.start(title = "creating user ...")
                    }

                    value.isError() -> {
                        progressDialog.stop()
                    }

                    else -> {}
                }
            }
        }
    }

    private fun updateUserObserver() {
        viewModel.updateUserRequest.observe(requireActivity()) { result ->
            result?.getContentIfNotHandled()?.let { value ->
                when {
                    value.isSuccess() -> {
                        value.data?.let { data ->
                            Toast.makeText(requireContext(), "User updated successfully", Toast.LENGTH_SHORT).show()
                        }
                        progressDialog.stop()
                    }

                    value.isLoading() -> {
                        progressDialog.start(title = "updating user ...")
                    }

                    value.isError() -> {
                        progressDialog.stop()
                    }

                    else -> {}
                }
            }
        }
    }

    private fun observeDelete() {
        viewModel.deleteUserRequest.observe(requireActivity()) { result ->
            result?.getContentIfNotHandled()?.let { value ->
                when {
                    value.isSuccess() -> {
                        value.data?.let { data ->
                            Toast.makeText(requireContext(), data, Toast.LENGTH_SHORT).show()
                            findNavController().navigateUp()
                        }
                        progressDialog.stop()
                    }

                    value.isLoading() -> {
                        progressDialog.start(title = "deleting user ...")
                    }

                    value.isError() -> {
                        progressDialog.stop()
                    }

                    else -> {}
                }
            }
        }
    }


    private fun resetFields() {
        binding.firstName.setText("")
        binding.lastName.setText("")
        binding.addressInput.setText("")
        binding.gender.setText("")
        binding.email.setText("")
        binding.phone.setText("")

        binding.selectFirstNameInput.isErrorEnabled = false
        binding.selectLastNameInput.isErrorEnabled = false
        binding.selectPhoneInput.isErrorEnabled = false
        binding.selectPhoneInput.isErrorEnabled = false
        binding.selectEmailInput.isErrorEnabled = false
        binding.selectAddressInput.isErrorEnabled = false
        binding.selectGenderInput.isErrorEnabled = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val args : CreateUpdateFragmentArgs by navArgs()
}