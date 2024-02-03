package com.sprial.emical.ui.themes.chip

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sprial.emical.R
import com.sprial.emical.databinding.FragmentThemeDefaultBinding
import com.sprial.emical.ui.MainViewModel
import com.sprial.emical.ui.MainViewModelFactory
import com.sprial.emical.utils.EmiPrefManager
import com.sprial.emical.utils.dataStore

class DefaultThemeFragment : Fragment() {

    private val tenureMonths = intArrayOf(1, 3, 6, 9, 12, 18, 24, 36)
    private val tenureYears =
        intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)

    private lateinit var binding: FragmentThemeDefaultBinding
    private lateinit var viewModel: MainViewModel

    private var amount: Int = 0
    private var tenure: Int = 1
    private var interest: Double = 7.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThemeDefaultBinding.bind(
            inflater.inflate(
                R.layout.fragment_theme_default,
                container,
                false
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()

        initUI()
        binding.btnEmiCalculate.setOnClickListener {
            startActivity(
                Intent().apply {
                    data = Uri.parse("shubidha://bbl?authToken=77889")
                }
            )
            calculateEmi()
        }

        binding.cvEmiDetails.setFavouriteClick {
            it?.let {
                viewModel.updateEmiHistory(it)
            } ?: kotlin.run {
                Toast.makeText(requireContext(), "Calculate EMI first !!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initialize() {
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(
                EmiPrefManager(requireContext().dataStore)
            )
        )[MainViewModel::class.java]
    }

    private fun initUI() {
        binding.cvEmiDetails.viewTreeObserver.addOnGlobalLayoutListener {
            val height = binding.cvEmiDetails.height / 2

            val layoutparam = binding.cvEmiInputs.layoutParams as ViewGroup.MarginLayoutParams
            layoutparam.setMargins(
                layoutparam.leftMargin,
                height,
                layoutparam.rightMargin,
                layoutparam.bottomMargin
            )
            binding.cvEmiInputs.requestLayout()
            binding.cvEmiDetails.viewTreeObserver.removeOnGlobalLayoutListener { }
        }
    }

    private fun calculateEmi() {
        amount = if (binding.tilLoanAmount.editText?.text?.toString()
                .isNullOrEmpty() || binding.tilLoanAmount.editText?.text?.toString()
                ?.toInt() ?: 0 <= 0
        ) 1000 else binding.tilLoanAmount.editText?.text?.toString()?.toInt() ?: 0

        interest = if (binding.tvInterestRate.editText?.text?.toString()
                .isNullOrEmpty() || binding.tvInterestRate.editText?.text?.toString()
                ?.toDouble() ?: 0.0 <= 0.0
        ) 7.0 else binding.tvInterestRate.editText?.text?.toString()?.toDouble() ?: 7.0

        tenure = if (binding.tvTenure.editText?.text?.toString()
                .isNullOrEmpty() || binding.tvTenure.editText?.text?.toString()?.toInt() ?: 0 <= 0
        ) 7 else binding.tvTenure.editText?.text?.toString()?.toInt() ?: 1

        binding.cvEmiDetails.calculateEmi(
             loanAmount = amount.toString(),
             tenureMonths = tenure.toString(),
            interestRate = interest.toString()
        )

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DefaultThemeFragment().apply {
            }
    }
}