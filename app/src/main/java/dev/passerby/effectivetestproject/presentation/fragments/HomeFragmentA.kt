package dev.passerby.effectivetestproject.presentation.fragments

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.text.Html
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
import dev.passerby.effectivetestproject.presentation.createObservable
import dev.passerby.effectivetestproject.presentation.viewmodels.HomeAViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class HomeFragmentA : Fragment() {

    private var _binding: FragmentHomeABinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeAViewModel
    private var mapper = SearchWordsMapper()
    private lateinit var searchRVAdapter: SearchRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeABinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[HomeAViewModel::class.java]
        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        //addTextChangeListeners()
        binding.homeASearchEt.createObservable()
            .doOnNext {
                showContainer()
                showLoading()
            }
            .debounce(1000, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val searchText =
                    StringBuilder().append(it.trim()).append("%").toString()
                if (it.isNotEmpty()) {
                    viewModel.getSearchWords()
                    viewModel.result.observe(viewLifecycleOwner) { response ->
                        when (response) {
                            is BaseResponse.Loading -> {}
                            is BaseResponse.Success -> {
                                stopLoading()
                                Log.d("HomeAFragment", "onViewCreated: Success")
                                if (response.data?.words?.isEmpty() == true) {
                                    throw Exception("No data in response")
                                } else {
                                    for (i in 0 until response.data?.words?.size!!) {
                                        viewModel.addSearchWord(
                                            mapper.mapEntityToDbModel(response.data, i)
                                        )
                                    }
                                    searchRVAdapter = SearchRVAdapter()
                                    viewModel.getSearchWordsFromDB(searchText)
                                        .observe(viewLifecycleOwner) { list ->
                                            list?.let { searchRVAdapter.updateList(list) }
                                        }
                                    Log.d("HomeAFragment", "onViewCreated: $it")
                                    initRV()
                                }
                            }
                            is BaseResponse.Error -> {
                                stopLoading()
                                closeContainer()
                            }
                        }
                    }
                } else {
                    stopLoading()
                    closeContainer()
                    Log.d("HomeAFragment", "onViewCreated: Empty")
                }
            }
    }

    private fun initViews() {
        val first = "Trade by "
        val next = "<font color='#4E55D7'>bata</font>"
        binding.homeATitle.apply {
            typeface = Typeface.DEFAULT_BOLD
            text = Html.fromHtml(first + next, 0)
        }
    }

    private fun initRV() {
        binding.rv.apply {
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )
            adapter = searchRVAdapter
        }
    }

    private fun showLoading() {
        binding.loading.visibility = View.VISIBLE
    }

    private fun showContainer() {
        binding.predictionsContainer.visibility = View.VISIBLE
    }

    private fun closeContainer() {
        binding.predictionsContainer.visibility = View.GONE
    }

    private fun stopLoading() {
        binding.loading.visibility = View.GONE
    }
}