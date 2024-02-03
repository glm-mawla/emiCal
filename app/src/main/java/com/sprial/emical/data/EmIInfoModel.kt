package com.sprial.emical.data

data class EmiInfoModel(
    val id: Int,
    val principal: Double,
    val tenure: Int,
    val interest: Double,
    val emiAmount: Double
)

data class UserPreferences(
    val showCompleted: Boolean,
    val sortOrder: String
)