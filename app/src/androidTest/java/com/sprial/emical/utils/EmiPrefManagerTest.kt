package com.sprial.emical.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
class EmiPrefManagerTest {

    private val testContext: Context = ApplicationProvider.getApplicationContext()
    private val testCoroutineDispatcher = StandardTestDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher + Job())

    private val testDataStore: DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            scope = testCoroutineScope,
            produceFile = {
                testContext.preferencesDataStoreFile("test_datastore")
            })

    private val preference = EmiPrefManager(testDataStore)

    @Before
    fun setUp(){
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun fetchDefaultEmiHistory() {
        testCoroutineScope.launch {
            assert(preference.fetchDefaultEmiHistory()?.id == 0)
        }
    }

    @Test
    fun fetchDefaultEmiTheme() {
        testCoroutineScope.launch {
            assert(preference.fetchDefaultEmiTheme() == EmiTheme.Default.name)
        }
    }
}