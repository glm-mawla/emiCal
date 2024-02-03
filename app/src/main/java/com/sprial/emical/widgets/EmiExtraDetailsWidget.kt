package com.sprial.emical.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.sprial.emical.R
import com.sprial.emical.data.EmiInfoModel
import com.sprial.emical.databinding.WidgetEmiDetailsExtraBinding
import com.sprial.emical.utils.EmiCalUtils
import com.sprial.emical.utils.formatCurrency
import com.sprial.emical.utils.toRoundTwoDigit

open class EmiExtraDetailsWidget : ConstraintLayout {

    private lateinit var binding: WidgetEmiDetailsExtraBinding
    private lateinit var onFavouriteClick: (EmiInfoModel?) -> Unit

    private var emiInfoModel: EmiInfoModel? = null

    constructor(
        context: Context
    ) : super(context)

    constructor(
        context: Context, attributeSet: AttributeSet
    ) : super(context, attributeSet)

    constructor(
        context: Context, attributeSet: AttributeSet, diffStyleAttr: Int
    ) : super(context, attributeSet, diffStyleAttr)

    init {
        val view =
            LayoutInflater.from(context).inflate(R.layout.widget_emi_details_extra, this, true)
        binding = WidgetEmiDetailsExtraBinding.bind(view)

        binding.ivFavourite.setOnClickListener {
            if (::onFavouriteClick.isInitialized) {
                onFavouriteClick.invoke(emiInfoModel)
                Toast.makeText(context, "Added to favourite", Toast.LENGTH_SHORT).show()
            }
        }
    }

    open fun calculateEmi(loanAmount: String?, interestRate: String?, tenureMonths: String?) {
        val amount = if (loanAmount.isNullOrEmpty() || loanAmount?.toDouble() ?: 0.0 <= 0.0) 1000.0
        else loanAmount?.toDouble() ?: 0.0

        val interest =
            if (interestRate.isNullOrEmpty() || interestRate?.toDouble() ?: 0.0 <= 0.0) 7.0
            else interestRate?.toDouble() ?: 7.0

        val tenure = if (tenureMonths.isNullOrEmpty() || tenureMonths?.toInt() ?: 0 <= 0) 7
        else tenureMonths?.toInt() ?: 1

        val emiCalUtils = EmiCalUtils()

        val emi: Double = emiCalUtils.getCalculatedEmi(
            loanAmount = amount.toDouble(),
            loanTenure = tenure,
            interestRate = interest
        )

        emiInfoModel = EmiInfoModel(
            id = 1,
            principal = amount,
            tenure = tenure,
            interest = interest,
            emiAmount = emi
        )

        val totalInterest = emi.times(tenure).minus(amount)
        val totalPayment = emi.times(tenure)

        binding.tvCalculatedEmi.text = "${emi.toInt().formatCurrency()}"
        binding.tvPrincipalCalculated.text = "${totalPayment.formatCurrency()}"
        binding.tvInterestCalculated.text = "${totalInterest.formatCurrency()}"
        binding.tvInterestRateCalculated.text = "@${interest.toRoundTwoDigit()}%\n$tenure months"
    }

    fun setFavouriteClick(onclick: (EmiInfoModel?) -> Unit) {
        onFavouriteClick = onclick
    }

    fun hideFavourite(){
        binding.ivFavourite.visibility = GONE
    }
}