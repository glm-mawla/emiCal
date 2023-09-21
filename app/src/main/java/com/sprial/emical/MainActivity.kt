package com.sprial.emical

import android.os.Bundle
import android.view.ViewGroup.MarginLayoutParams
import androidx.appcompat.app.AppCompatActivity
import com.sprial.emical.databinding.ActivityMainBinding
import com.sprial.emical.utils.EmiCalUtils
import com.sprial.emical.utils.formatCurrency


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        binding.btnEmiCalculate.setOnClickListener {
            calculateEmi()
        }
    }

    private fun initUI() {
        binding.cvEmiDetails.viewTreeObserver.addOnGlobalLayoutListener {
            val height = binding.cvEmiDetails.height /2

            val layoutparam = binding.cvEmiInputs.layoutParams as MarginLayoutParams
            layoutparam.setMargins(layoutparam.leftMargin, height, layoutparam.rightMargin, layoutparam.bottomMargin)
            binding.cvEmiInputs.requestLayout()
            binding.cvEmiDetails.viewTreeObserver.removeOnGlobalLayoutListener {  }
        }
    }

    private fun calculateEmi() {
        val emiCalUtils = EmiCalUtils()
        val amount = binding.tilLoanAmount.editText?.text?.toString()
        val tenure = binding.tvTenure.editText?.text?.toString()
        val interest = binding.tvInterestRate.editText?.text?.toString()

        val emi: Double = emiCalUtils.getCalculatedEmi(
            loanAmount = if(amount.isNullOrEmpty()) 0.0 else amount?.toDouble() ?: 10000.0,
            loanTenure = if (tenure.isNullOrEmpty()) 1 else tenure?.toInt() ?: 1,
            interestRate = if(interest.isNullOrEmpty()) 7.0 else interest?.toDouble() ?: 7.0
        )

        binding.tvCalculatedEmi.text = "${emi.formatCurrency()}"
        binding.tvInterest.text = "monthly"
    }
}