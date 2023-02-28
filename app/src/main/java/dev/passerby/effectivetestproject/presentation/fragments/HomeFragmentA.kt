package dev.passerby.effectivetestproject.presentation.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.passerby.effectivetestproject.R
import dev.passerby.effectivetestproject.databinding.FragmentHomeABinding

class HomeFragmentA : Fragment() {

    private var _binding: FragmentHomeABinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.item.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.child_container, HomeFragmentB())
                .addToBackStack(null)
                .commit()
        }

        val first = "Trade by "
        val next = "<font color='#4E55D7'>bata</font>"
        binding.homeATitle.apply {
            typeface = Typeface.DEFAULT_BOLD
            text = Html.fromHtml(first + next, 0)
        }
    }
}