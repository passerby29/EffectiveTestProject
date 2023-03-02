package dev.passerby.effectivetestproject.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.passerby.effectivetestproject.databinding.FragmentLoginBinding
import dev.passerby.effectivetestproject.presentation.viewmodels.LoginViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LoginViewModel
    private lateinit var onEditingFinishedListenerLogin: OnEditingFinishedListenerLogin

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnEditingFinishedListenerLogin) {
            onEditingFinishedListenerLogin = context
        } else {
            throw RuntimeException("Activity  must implement OnEditingFinishedListenerLogin")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginBtn.setOnClickListener {
            val firstName = binding.loginFirstNameEt.text.toString()
            viewModel.getUser(firstName)
        }
        addTextChangeListeners()
        loginUser()
        observeViewModel()
    }

    private fun loginUser() {
        binding.loginBtn.setOnClickListener {
            val inputFirstName = binding.loginFirstNameEt.text?.toString()
            viewModel.getUser(inputFirstName)
        }
    }

    private fun observeViewModel() {
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            onEditingFinishedListenerLogin.onEditingFinishedLogin()
        }
        viewModel.errorInputFirstName.observe(viewLifecycleOwner) {
            val msg = if (it) {
                "Invalid First Name"
            } else {
                null
            }
            binding.loginFirstNameEt.error = msg
        }
    }

    private fun addTextChangeListeners() {
        binding.loginFirstNameEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputFirstName()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    interface OnEditingFinishedListenerLogin {
        fun onEditingFinishedLogin() {}
    }
}