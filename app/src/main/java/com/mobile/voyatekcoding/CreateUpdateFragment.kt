package com.mobile.voyatekcoding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
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

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
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
            binding.firstName.setText(args.userDetail.firstName)
            binding.lastName.setText(args.userDetail.lastName)
            binding.addressInput.setText(args.userDetail.address)
            binding.gender.setText(args.userDetail.gender)
            binding.email.setText(args.userDetail.email)
            binding.phone.setText(args.userDetail.phoneNumber)
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
                        viewModel.updateUser(UserResponseData(
                            firstName = firstName,
                            lastName = lastName,
                            email = email,
                            phoneNumber = phoneNumber,
                            gender = gender,
                            address = address,
                            id = args.userDetail.id
                        ))
                    } else {
                        viewModel.createUser(UserResponseData(
                            firstName = firstName,
                            lastName = lastName,
                            email = email,
                            phoneNumber = phoneNumber,
                            gender = gender,
                            address = address,
                            id = args.userDetail.id
                        ))
                    }
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val args : CreateUpdateFragmentArgs by navArgs()
}