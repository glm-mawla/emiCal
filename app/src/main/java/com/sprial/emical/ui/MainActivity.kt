package com.sprial.emical.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sprial.emical.databinding.ActivityMainBinding
import com.sprial.emical.ui.history.EmiHistoryFragment
import com.sprial.emical.ui.themes.chip.DefaultThemeFragment
import com.sprial.emical.ui.themes.chip.SeekThemeFragment
import com.sprial.emical.ui.themes.chip.ThemeChipFragment
import com.sprial.emical.utils.EmiPrefManager
import com.sprial.emical.utils.EmiTheme
import com.sprial.emical.utils.dataStore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var defaultThemeFragment: DefaultThemeFragment
    private lateinit var seekThemeFragment: SeekThemeFragment
    private lateinit var chipFragment: ThemeChipFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
        initObserver()
        initUI()
        // ATTENTION: This was auto-generated to handle app links.
        val appLinkIntent: Intent = intent
        val appLinkAction: String? = appLinkIntent.action
        val appLinkData: Uri? = appLinkIntent.data

        Toast.makeText(this, "$appLinkAction + $appLinkData", Toast.LENGTH_SHORT).show()
    }

    private fun initObserver() {
        viewModel.defaultEmiTHeme.observe(this) { theme ->
            if (theme == EmiTheme.Seeker.name) {
                binding.chipSeeker.isChecked = true
                if (!::seekThemeFragment.isInitialized) {
                    seekThemeFragment = SeekThemeFragment.newInstance()
                }
                supportFragmentManager.beginTransaction()
                    .replace(binding.fragmentContainer.id, seekThemeFragment)
                    .commitAllowingStateLoss()


            } else {
                binding.chipDefault.isChecked = true

                if (!::defaultThemeFragment.isInitialized) {
                    defaultThemeFragment = DefaultThemeFragment.newInstance("", "")
                }
                supportFragmentManager.beginTransaction()
                    .replace(binding.fragmentContainer.id, defaultThemeFragment)
                    .commitAllowingStateLoss()
            }
        }
    }

    private fun initialize() {
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(
                EmiPrefManager(dataStore)
            )
        )[MainViewModel::class.java]
    }

    private fun initUI() {
        binding.chipDefault.setOnClickListener {
            viewModel.updateEmiTheme(EmiTheme.Default)
            if (!::defaultThemeFragment.isInitialized) {
                defaultThemeFragment = DefaultThemeFragment.newInstance("", "")
            }
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, defaultThemeFragment)
                .commitAllowingStateLoss()
        }

        binding.chipSeeker.setOnClickListener {
            viewModel.updateEmiTheme(EmiTheme.Seeker)

            if (!::seekThemeFragment.isInitialized) {
                seekThemeFragment = SeekThemeFragment.newInstance()
            }
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, seekThemeFragment)
                .commitAllowingStateLoss()
        }

        binding.chipMedia.setOnClickListener {
            if (!::defaultThemeFragment.isInitialized) {
                chipFragment = ThemeChipFragment.newInstance("", "")
            }
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, chipFragment)
                .commitAllowingStateLoss()
        }

        binding.ivFavourites.setOnClickListener {
            binding.chipDefault.isChecked = false
            binding.chipSeeker.isChecked = false

            val emiHistoryFragment = EmiHistoryFragment.newInstance()

            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, emiHistoryFragment)
                .commitAllowingStateLoss()
        }

    }

}