package com.sprial.emical.utils

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.GsonBuilder
import com.sprial.emical.data.EmiInfoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

enum class EmiTheme {
    Default, Seeker
}

class EmiPrefRepository(private val dataStore: DataStore<Preferences>) {

    private val TAG = "DataStore_EiHistory"

    private object PreferenceKeys {
        val EMI_HISTORY = stringPreferencesKey("emi_history")
        val EMI_THEME = stringPreferencesKey("emi_theme")
    }

    suspend fun emiHistoryFlow(): Flow<EmiInfoModel?> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    Log.e(TAG, "Error reading preferences.", exception)
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map {
                mapEmiHistory(it)
            }
    }

    suspend fun emiTheme(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    Log.e(TAG, "Error reading preferences.", exception)
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preference ->
                mapEmiTheme(preference)
            }
    }

    suspend fun updateEmiTheme(emiTheme: EmiTheme) {
        dataStore.edit { preference ->
            preference[PreferenceKeys.EMI_THEME] = emiTheme.name
        }
    }

    suspend fun fetchDefaultEmiHistory(): EmiInfoModel? =
        mapEmiHistory(dataStore.data.first().toPreferences())

    suspend fun fetchDefaultEmiTheme(): String =
        mapEmiTheme(dataStore.data.first().toPreferences())

    suspend fun updateEmiHistory(emiInfoModel: EmiInfoModel) {
        dataStore.edit { preference ->
            val historyJson = GsonBuilder().create().toJson(emiInfoModel)
            preference[PreferenceKeys.EMI_HISTORY] = historyJson
        }
    }

    private fun mapEmiHistory(preferences: Preferences): EmiInfoModel? {
        val emiHistoryPref = preferences[PreferenceKeys.EMI_HISTORY]

        emiHistoryPref?.let {
            return GsonBuilder().create().fromJson(it, EmiInfoModel::class.java)
        }

        return null
    }

    private fun mapEmiTheme(preference: Preferences) =
        preference[PreferenceKeys.EMI_THEME] ?: EmiTheme.Seeker.name

}