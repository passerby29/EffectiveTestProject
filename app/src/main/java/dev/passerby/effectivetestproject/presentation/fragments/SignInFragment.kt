package dev.passerby.effectivetestproject.presentation.fragments

import android.os.Bundle
import android.text.*
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.passerby.effectivetestproject.R
import dev.passerby.effectivetestproject.databinding.FragmentSignInBinding
import dev.passerby.effectivetestproject.presentation.viewmodels.SignInViewModel

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[SignInViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signItHaveAccountTv.apply {
            setOnClickListener {
                changeFragment()
            }
        }
        initViews()
        addTextChangeListeners()
        registerUser()
        observeViewModel()
    }

    private fun registerUser() {
        binding.signInBtn.setOnClickListener {
            val inputFirstName = binding.signInFirstNameEt.text?.toString()
            val inputLastName = binding.signInLastNameEt.text?.toString()
            val inputEmail = binding.signInEmailEt.text?.toString()
            viewModel.addUser(inputFirstName, inputLastName, inputEmail)
            Toast.makeText(requireContext(), "Account created successfully!", Toast.LENGTH_SHORT)
                .show()
//            changeFragment()
        }
    }

    private fun observeViewModel() {
        viewModel.errorInputFirstName.observe(viewLifecycleOwner) {
            val msg = if (it) {
                "Invalid First Name"
            } else {
                null
            }
            binding.signInFirstNameEt.error = msg
        }
        viewModel.errorInputLastName.observe(viewLifecycleOwner) {
            val msg = if (it) {
                "Invalid Last Name"
            } else {
                null
            }
            binding.signInLastNameEt.error = msg
        }
        viewModel.errorInputEmail.observe(viewLifecycleOwner) {
            val msg = if (it) {
                "Invalid email"
            } else {
                null
            }
            binding.signInEmailEt.error = msg
        }
    }

    private fun changeFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.startContainer, LoginFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun initViews() {
        val first = "Already have an account? "
        val next = "<font color='#254FE6'>Log in!</font>"
        binding.signItHaveAccountTv.text = Html.fromHtml(first + next, 0)
    }

    private fun addTextChangeListeners() {
        binding.signInFirstNameEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputFirstName()
            }

            override fun afterTextChanged(s: Editable?) {}

        })
        binding.signInLastNameEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputLastName()
            }

            override fun afterTextChanged(s: Editable?) {}

        })
        binding.signInEmailEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputEmail()
            }

            override fun afterTextChanged(s: Editable?) {}

        })
    }
}