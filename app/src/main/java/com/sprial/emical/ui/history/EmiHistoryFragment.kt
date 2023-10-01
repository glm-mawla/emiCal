package com.sprial.emical.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sprial.emical.R
import com.sprial.emical.data.EmiInfoModel
import com.sprial.emical.databinding.FragmentEmiHistoryBinding
import com.sprial.emical.ui.dataStore
import com.sprial.emical.utils.EmiPrefRepository

class EmiHistoryFragment : Fragment() {

    companion object {
        fun newInstance() = EmiHistoryFragment()
    }

    private lateinit var binding: FragmentEmiHistoryBinding
    private lateinit var viewModel: EmiHistoryViewModel

    private val history = mutableListOf<EmiInfoModel>()
    private val historyAdapter: HistoryAdapter by lazy {
        HistoryAdapter(history) { item ->

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmiHistoryBinding.bind(
            inflater.inflate(
                R.layout.fragment_emi_history,
                container,
                false
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        initView()

        viewModel.getEmiHistory.observe(viewLifecycleOwner) { it ->
            it?.let { emi ->
                history.add(emi)
                historyAdapter.notifyDataSetChanged()
            }?: kotlin.run {
                Toast.makeText(requireContext(), "Add Favourite EMI information", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initView() {
        binding.rvEmiHistory.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = historyAdapter
        }
    }

    private fun initialize() {
        viewModel = ViewModelProvider(
            this,
            EmiViewModelFactory(
                EmiPrefRepository(requireContext().dataStore)
            )
        )[EmiHistoryViewModel::class.java]
    }
}