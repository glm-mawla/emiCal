package com.sprial.emical.ui

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.sprial.emical.utils.FakeEmiPrefManager
import com.sprial.emical.utils.dataStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get: Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    val context = ApplicationProvider.getApplicationContext<Context>()

    private lateinit var viewModel: MainViewModel
    private lateinit var dataStore: DataStore<Preferences>

    @Before
    fun setUp() {
        dataStore = context.dataStore
        viewModel = MainViewModel(FakeEmiPrefManager(dataStore))
    }

    @Test
    fun `test dataStore properly initiated`(){
        assertThat(dataStore == null).isFalse()
    }
}