package com.sprial.emical.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

class FakeEmiPrefManager(
    private val dataStore: DataStore<Preferences>
) : EmiPrefManager(dataStore) {
}