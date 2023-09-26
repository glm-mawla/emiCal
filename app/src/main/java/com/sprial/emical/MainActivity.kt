package com.sprial.emical

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sprial.emical.databinding.ActivityMainBinding
import com.sprial.emical.ui.themes.chip.DefaultThemeFragment
import com.sprial.emical.ui.themes.chip.SeekThemeFragment
import com.sprial.emical.ui.themes.chip.ThemeChipFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var defaultThemeFragment: DefaultThemeFragment
    private lateinit var seekThemeFragment: SeekThemeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

    }

    private fun initUI() {
        if (!::defaultThemeFragment.isInitialized) {
            defaultThemeFragment = DefaultThemeFragment.newInstance("", "")
        }

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, defaultThemeFragment)
            .commitAllowingStateLoss()

        binding.chipDefault.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, defaultThemeFragment)
                .commitAllowingStateLoss()
        }

        binding.chipSeeker.setOnClickListener {
            if (!::seekThemeFragment.isInitialized) {
                seekThemeFragment = SeekThemeFragment.newInstance()
            }
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, seekThemeFragment)
                .commitAllowingStateLoss()
        }

        binding.chipMedia.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, ThemeChipFragment.newInstance("", ""))
                .commitAllowingStateLoss()
        }

    }

}