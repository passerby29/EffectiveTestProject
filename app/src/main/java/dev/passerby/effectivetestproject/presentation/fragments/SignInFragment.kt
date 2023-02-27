package dev.passerby.effectivetestproject.presentation.fragments

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.passerby.effectivetestproject.R
import dev.passerby.effectivetestproject.databinding.FragmentSignInBinding


class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signItHaveAccountTv.apply {
            setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.startContainer, LoginFragment())
                    .addToBackStack(null)
                    .commit()
            }

            val first = "Already have an account? "
            val next = "<font color='#254FE6'>Log in!</font>"
            text = Html.fromHtml(first + next, 0)
        }
    }
}