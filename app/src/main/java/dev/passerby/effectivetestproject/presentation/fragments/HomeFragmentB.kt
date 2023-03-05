package dev.passerby.effectivetestproject.presentation.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import dev.passerby.effectivetestproject.data.server.BaseResponse
import dev.passerby.effectivetestproject.databinding.FragmentHomeBBinding
import dev.passerby.effectivetestproject.presentation.adapters.SelectedItemRVAdapter
import dev.passerby.effectivetestproject.presentation.viewmodels.HomeBViewModel

class HomeFragmentB : Fragment(), SelectedItemRVAdapter.ItemClickListener {

    private var _binding: FragmentHomeBBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeBViewModel
    private lateinit var selectedItemRVAdapter: SelectedItemRVAdapter
    private var number = 1.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[HomeBViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData() {
        viewModel.getSelectedItem()
        viewModel.result.observe(viewLifecycleOwner) { response ->
            when (response) {
                is BaseResponse.Loading -> {}
                is BaseResponse.Success -> {
                    Log.d("HomeBFragment", "loadData: Success")
                    if (response.data == null) {
                        throw Exception("No data in response")
                    } else {
                        val list = response.data.image_urls
                        addCarousel(list)
                        Picasso.get().load(list[0]).into(binding.homeBMainIv)
                        binding.homeBItemNameTv.text = response.data.name
                        binding.homeBItemDescTv.text = response.data.description
                        binding.homeBRatingTv.text = response.data.rating.toString()
                        val numberOfReviews = "(${response.data.number_of_reviews})"
                        binding.homeBNumberOfReviewsTv.text = numberOfReviews
                        val price = "$ ${response.data.price}"
                        binding.homeBItemPriceTv.text = price
                        binding.homeBColor1.apply {
                            setCardBackgroundColor(Color.parseColor(response.data.colors[0]))
                            setOnClickListener { selectColor(response.data.colors[0]) }
                        }
                        binding.homeBColor2.apply {
                            setCardBackgroundColor(Color.parseColor(response.data.colors[1]))
                            setOnClickListener { selectColor(response.data.colors[1]) }
                        }
                        binding.homeBColor3.apply {
                            setCardBackgroundColor(Color.parseColor(response.data.colors[2]))
                            setOnClickListener { selectColor(response.data.colors[2]) }
                        }
                        binding.homeBPlusBtn.setOnClickListener {
                            number++
                            setPriceBtn(response.data.price)
                        }
                        binding.homeBMinusBtn.setOnClickListener {
                            number--
                            if (number < 0) {
                                Toast.makeText(
                                    requireContext(),
                                    "Quantity cannot be less than 0",
                                    Toast.LENGTH_SHORT
                                ).show()
                                number = 0.0
                            } else {
                                setPriceBtn(response.data.price)
                            }
                        }
                        setPriceBtn(response.data.price)
                        binding.homeBBackBtn.setOnClickListener {
                            parentFragmentManager.popBackStack()
                        }
                    }
                }
                is BaseResponse.Error -> {}
                else -> {}
            }
        }
    }

    private fun setPriceBtn(price: Int) {
        val count = number * price
        val priceBtn = "$ ${count.toInt()}\tAdd to cart"
        binding.homeBAddToCartBtn.text = priceBtn
    }

    private fun selectColor(selected: String) {
        Toast.makeText(requireContext(), "Selected color $selected", Toast.LENGTH_SHORT).show()
    }

    private fun addCarousel(list: List<String>) {
        selectedItemRVAdapter = SelectedItemRVAdapter(list, this)
        binding.imagesRv.apply {
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
            adapter = selectedItemRVAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onItemClick(url: String) {
        Picasso.get().load(url).into(binding.homeBMainIv)
        selectedItemRVAdapter.selectedItem = url
        selectedItemRVAdapter.notifyDataSetChanged()
    }
}