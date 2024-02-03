package com.sprial.emical.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test


class EmiCalUtilsTest {

    private lateinit var emiCalUtils : EmiCalUtils

    @Before
    fun setUp() {
        emiCalUtils = EmiCalUtils()
    }

    @Test
    fun `default interest rate test`() {
        val getEmi = emiCalUtils.getCalculatedEmi(1000.0,1)

        assertThat(getEmi).isNonZero()
    }

    @Test
    fun `0 loan amount passing make error`() {
        val getEmi = emiCalUtils.getCalculatedEmi(loanAmount = 0.0, loanTenure = 1, interestRate = 7.0)

        assertThat(getEmi).isNonZero()
    }

    @Test
    fun `non zero emi calculation`() {
        val getEmi = emiCalUtils.getCalculatedEmi(1000.0,1,8.0)

        assertThat(getEmi).isZero()
    }

    @Test
    fun getAddition(){
        val add = emiCalUtils.getAddition(2,3)

        assertThat(add).isEqualTo(5)
    }
}