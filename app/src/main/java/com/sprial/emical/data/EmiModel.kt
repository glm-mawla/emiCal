package com.sprial.emical.data

import java.io.InputStream

data class EmiModel(
    val emiId: Int,
    val principalAmount: Double,
    val tenure: Int,
    val interest: Double
) {
    companion object {
        fun parseFrom(input: InputStream): EmiModel {
            return EmiModel(1, 1.0, 1, 1.0)
        }

        fun getDefaultInstance(): EmiModel {
            return EmiModel(1, 1.0, 1, 1.0)
        }
    }
}