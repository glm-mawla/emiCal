package com.sprial.emical.utils

class EmiCalUtils {

    fun getCalculatedEmi(
        loanAmount: Double,
        loanTenure: Int,
        interestRate: Double = 7.00
    ): Double {
        val interestRateDecimal = interestRate / (12 * 100);
        val months = loanTenure.toDouble()
        val rPower = Math.pow(1 + interestRateDecimal, months)

        var monthlyPayment = loanAmount / months
        if (rPower > 1) {
            monthlyPayment = loanAmount * interestRateDecimal * rPower / (rPower - 1)
        }
        return monthlyPayment
    }
}