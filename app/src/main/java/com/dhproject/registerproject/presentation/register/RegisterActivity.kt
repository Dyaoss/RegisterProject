package com.dhproject.registerproject.presentation.register

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dhproject.registerproject.R
import com.dhproject.registerproject.data.RegisterViewModel
import com.dhproject.registerproject.databinding.ActivityRegisterBinding
import com.dhproject.registerproject.presentation.register.signin.SigninFragment

class RegisterActivity : AppCompatActivity() {
    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }

    private val viewModel: RegisterViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //초기 프래그먼트 설정
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.register_fragment_container, SigninFragment())
                .commit()
        }


    }
}