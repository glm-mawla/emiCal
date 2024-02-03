package com.sprial.emical.ui.themes.chip

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.sprial.emical.R
import com.sprial.emical.databinding.FragmentThemeChipBinding
import com.sprial.emical.utils.EmiCalUtils
import com.sprial.emical.utils.formatCurrency

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThemeChipFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThemeChipFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val tenureMonths = intArrayOf(1, 3, 6, 9, 12, 18, 24, 36)
    private val tenureYears =
        intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)

    private lateinit var binding: FragmentThemeChipBinding
    private var amount = ""
    private var tenure = ""
    private var interest = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThemeChipBinding.bind(
            inflater.inflate(
                R.layout.fragment_theme_chip,
                container,
                false
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        binding.rvTenure.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = SingleSelectionAdapter(tenureMonths.toMutableList())
        }
    }

    private fun getChipItem(title: String): Chip {

        val chipItem =
            /*layoutInflater.inflate(R.layout.chip_item, binding.chipTenure, false) as Chip*/
            Chip(requireContext(), null, R.style.ChipStyle)
        chipItem.text = title
        chipItem.isChipIconVisible = true
        chipItem.isCloseIconVisible = false
        chipItem.isClickable = true
        chipItem.isCheckable = false
//        chipItem.chipBackgroundColor =
//            ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.chip_bg))
        chipItem.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.chip_text
                )
            )
        )

        chipItem.setChipBackgroundColorResource(R.color.chip_bg)

        chipItem.setOnClickListener {
            tenure = chipItem.text?.toString()?.trim() ?: ""
//            chipItem.isChecked = true
//            chipItem.isEnabled = true
        }
        return chipItem
    }

    private fun initUI() {
        //create chip items
        for (tenureMonth in tenureMonths) {
//            binding.chipTenure.addView(getChipItem("$tenureMonth") as View)
        }

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

        binding.chipTenure.setOnCheckedStateChangeListener { group, checkedIds ->
            val checkedChip = group.findViewById<Chip>(checkedIds[0])
            tenure = checkedChip.text?.toString()?.trim() ?: ""
        }

        binding.btnEmiCalculate.setOnClickListener {
            calculateEmi()
        }
    }

    private fun calculateEmi() {
        val emiCalUtils = EmiCalUtils()
        amount = binding.tilLoanAmount.editText?.text?.toString() ?: ""
        interest = binding.tvInterestRate.editText?.text?.toString() ?: ""

        val emi: Double = emiCalUtils.getCalculatedEmi(
            loanAmount = if (amount.isNullOrEmpty() || amount.toInt() <= 0) 1000.0 else amount?.toDouble()
                ?: 10000.0,
            loanTenure = if (tenure.isNullOrEmpty() || tenure.toInt() <= 0) 1 else tenure?.toInt()
                ?: 1,
            interestRate = if (interest.isNullOrEmpty() || interest.toDouble() <= 0.0) 7.0 else interest?.toDouble()
                ?: 7.0
        )

        binding.tvCalculatedEmi.text = "${emi.formatCurrency()}"
        binding.tvInterestCalculated.text = "monthly"
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ThemeChipFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}