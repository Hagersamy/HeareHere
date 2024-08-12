package com.example.hearehere.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hearehere.R
import com.example.hearehere.register.viewmodel.RegisterDataViewModel
import com.example.hearehere.databinding.FragmentSignUpBinding

class SignUp : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterDataViewModel by lazy {
        ViewModelProvider(this)[RegisterDataViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.registerButton.setOnClickListener {
            val name = binding.textInputEditTexfullname.text.toString()
            val email = binding.textInputEditTextEmail.text.toString()
            val password = binding.textInputEditTextPassword.text.toString()
            viewModel.registerUser(name, email, password)
        }
        observeUserRegister()
        observeError()

        super.onViewCreated(view, savedInstanceState)
        binding.logtxt.setOnClickListener{
            findNavController().navigate(R.id.action_signUp_to_login)
        }
       binding.registerButton.setOnClickListener{
           findNavController().navigate(R.id.action_signUp_to_login)
       }
    }

    private fun observeUserRegister() {
        viewModel.registerData.observe(viewLifecycleOwner) { registerData ->
            registerData?.let {
                Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeError() {
        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(), "Error in your Registration: $it", Toast.LENGTH_SHORT).show()
            }
        }
    }
}