package com.sprial.emical.ui.themes.chip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sprial.emical.R
import com.sprial.emical.databinding.DialogEmiCalculationBinding
import com.sprial.emical.ui.MainViewModel
import com.sprial.emical.ui.MainViewModelFactory
import com.sprial.emical.ui.dataStore
import com.sprial.emical.utils.EmiPrefRepository
import com.sprial.emical.utils.formatCurrency
import com.sprial.emical.utils.toRoundTwoDigit

class SeekThemeFragment : Fragment() {

    private lateinit var binding: DialogEmiCalculationBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogEmiCalculationBinding.bind(
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_emi_calculation, null)
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        initView()
    }

    private fun initialize() {
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(
                EmiPrefRepository(requireContext().dataStore)
            )
        )[MainViewModel::class.java]
    }

    private fun initView(){
        var amount: Double = 1000.0
        var tenure: Int = 1
        var rate: Double = 7.0

        binding.seekBarAmount.addOnChangeListener { slider, value, fromUser ->
            amount = value.toDouble()
            binding.cvEmiDetails.calculateEmi(amount.toString(), rate.toString(), tenure.toString())
        }
        binding.seekBarAmount.setLabelFormatter {
            " ${it.toInt().formatCurrency()} "
        }

        binding.seekBarTenure.addOnChangeListener { slider, value, fromUser ->
            tenure = value.toInt()
            binding.cvEmiDetails.calculateEmi(amount.toString(), rate.toString(), tenure.toString())
        }
        binding.seekBarTenure.setLabelFormatter {
            " ${it.toInt()} months"
        }

        binding.seekBarRate.addOnChangeListener { slider, value, fromUser ->
            rate = value.toDouble()
            binding.cvEmiDetails.calculateEmi(amount.toString(), rate.toString(), tenure.toString())
        }
        binding.seekBarRate.setLabelFormatter {
            " ${it.toRoundTwoDigit()}% "
        }

        binding.cvEmiDetails.setFavouriteClick {
            it?.let {
                viewModel.updateEmiHistory(it)
            } ?: kotlin.run {
                Toast.makeText(requireContext(), "Calculate EMI first !!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        fun newInstance() =
            SeekThemeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

}