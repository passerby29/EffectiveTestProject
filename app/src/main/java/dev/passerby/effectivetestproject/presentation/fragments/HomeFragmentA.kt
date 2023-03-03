package dev.passerby.effectivetestproject.presentation.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dev.passerby.effectivetestproject.data.room.SearchWordsMapper
import dev.passerby.effectivetestproject.data.server.BaseResponse
import dev.passerby.effectivetestproject.databinding.FragmentHomeABinding
import dev.passerby.effectivetestproject.presentation.adapters.SearchRVAdapter
import dev.passerby.effectivetestproject.presentation.viewmodels.HomeAViewModel

class HomeFragmentA : Fragment() {

    private var _binding: FragmentHomeABinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeAViewModel
    private var mapper = SearchWordsMapper()
    private lateinit var searchRVAdapter: SearchRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeABinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[HomeAViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        addTextChangeListeners()
    }

    private fun addTextChangeListeners() {
        binding.homeASearchEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val searchText =
                    StringBuilder().append(s?.trim()).append("%").toString()
                viewModel.getSearchWords()
                viewModel.result.observe(viewLifecycleOwner) {
                    when (it) {
                        is BaseResponse.Loading -> {
                            Log.d("HomeAFragment", "afterTextChanged: Loading")
                        }
                        is BaseResponse.Success -> {
                            Log.d("HomeAFragment", "Success")
                            if (it.data?.words?.isEmpty() == true) {
                                throw Exception("List must be not null")
                            } else {
                                Log.d("HomeAFragment", it.data?.words.toString())
                                for (i in 0 until it.data?.words?.size!!) {
                                    viewModel.addSearchWord(mapper.mapEntityToDbModel(it.data, i))
                                    Log.d("HomeAFragment", "add Success")
                                }
                                searchRVAdapter = SearchRVAdapter()
                                viewModel.getSearchWordsFromDB(searchText)
                                    .observe(viewLifecycleOwner) { list ->
                                        list?.let { searchRVAdapter.updateList(list) }
                                    }
                                binding.rv.apply {
                                    layoutManager =
                                        LinearLayoutManager(
                                            requireContext(),
                                            LinearLayoutManager.VERTICAL,
                                            false
                                        )
                                    adapter = searchRVAdapter
                                }
                            }
                        }
                        is BaseResponse.Error -> {
                            Log.d("HomeAFragment", "Error")
                        }
                    }
                }
            }
        })
    }

    private fun initViews() {
        val first = "Trade by "
        val next = "<font color='#4E55D7'>bata</font>"
        binding.homeATitle.apply {
            typeface = Typeface.DEFAULT_BOLD
            text = Html.fromHtml(first + next, 0)
        }
    }
}