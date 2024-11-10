package com.dhproject.registerproject.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dhproject.registerproject.R
import com.dhproject.registerproject.databinding.ActivityMainBinding
import com.dhproject.registerproject.databinding.ActivityRegisterBinding
import com.dhproject.registerproject.presentation.register.RegisterActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val userId = intent.getStringExtra("user")
        binding.tvUserid.text = "$userId 님 환영합니다!"

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
            finish()
        }

    }
}