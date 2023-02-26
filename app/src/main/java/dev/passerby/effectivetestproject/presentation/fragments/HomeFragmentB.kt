package dev.passerby.effectivetestproject.presentation.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.passerby.effectivetestproject.R
import dev.passerby.effectivetestproject.presentation.viewmodels.HomeBViewModel

class HomeFragmentB : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_b, container, false)
    }
}