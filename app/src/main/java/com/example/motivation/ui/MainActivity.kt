package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.motivation.R
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.infra.USER_NAME
import com.example.motivation.databinding.ActivityMainBinding
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.repository.Mock

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.buttonNewPhrase.setOnClickListener(this)
        binding.iconAll.setOnClickListener(this)
        binding.iconHappy.setOnClickListener(this)
        binding.iconSunny.setOnClickListener(this)

        handleName()
        handleMenuFilter(R.id.icon_all)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_new_phrase) {
            handlePhrase()
        } else if (v.id in listOf(R.id.icon_all, R.id.icon_happy, R.id.icon_sunny)) {
            handleMenuFilter(v.id)
        }
    }

    private fun handleMenuFilter(id: Int) {
        binding.iconAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.iconHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.iconSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.icon_all -> {
                binding.iconAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.ALL
            }
            R.id.icon_happy -> {
                binding.iconHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.HAPPY
            }
            R.id.icon_sunny -> {
                binding.iconSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY
            }
        }
    }

    private fun handleName() {
        val name = SecurityPreferences(this).getKeyValue(MotivationConstants.KEY.USER_NAME)
        binding.textHello.text = "Olá, $name!"
    }

    private fun handlePhrase() {
        binding.textPhrase.text = Mock().getPhrase(categoryId)
    }
}