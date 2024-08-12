package com.example.hearehere.login

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
import com.example.hearehere.databinding.FragmentLoginBinding
import com.example.hearehere.login.viewmodel.LoginDataViewModel


class Login : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginDataViewModel by lazy {
        ViewModelProvider(this)[LoginDataViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sigtxt.setOnClickListener{
            findNavController().navigate(R.id.action_login_to_signUp)
        }
        binding.logbutt.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            viewModel.loginUser(email, password)
            findNavController().navigate(R.id.action_login_to_home2)
        }
        observeUserLogin()
        observeError()

    }
    private fun observeUserLogin() {
        viewModel.loginData.observe(viewLifecycleOwner) { loginData ->
            loginData?.let {
                Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeError() {
        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(), "Error in your Login: $it", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}