package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import com.example.motivation.R
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.infra.USER_NAME
import com.example.motivation.databinding.ActivityUserBinding
import com.example.motivation.infra.MotivationConstants

class UserActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.buttonSaveName.setOnClickListener(this)
        verifyUserName()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save_name) {
            handleSave()
        }
    }

    private fun verifyUserName() {
        val name = SecurityPreferences(this).getKeyValue(MotivationConstants.KEY.USER_NAME)
        if (name.isNotEmpty()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun handleSave() {
        val name = binding.editName.text.toString()
        if (name.isEmpty()) {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
            return
        }

        SecurityPreferences(this).store(MotivationConstants.KEY.USER_NAME, name)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
