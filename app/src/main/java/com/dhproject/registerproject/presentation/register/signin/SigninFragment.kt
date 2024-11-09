package com.dhproject.registerproject.presentation.register.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dhproject.registerproject.R
import com.dhproject.registerproject.data.RegisterViewModel
import com.dhproject.registerproject.databinding.FragmentSigninBinding
import com.dhproject.registerproject.presentation.register.signup.SignupFragment
import io.github.muddz.styleabletoast.StyleableToast


class SigninFragment : Fragment() {

    private lateinit var binding: FragmentSigninBinding
    private val viewModel: RegisterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSigninBinding.inflate(inflater, container, false)

        binding.btnSignin.setOnClickListener {
            val userId = binding.etSigninId.text.toString()
            val password = binding.etSigninPassword.text.toString()
            viewModel.loginUser(userId, password) { isSuccess, user ->
                if (isSuccess && user != null) {
                    // 로그인 성공
                } else {
                    StyleableToast.makeText(
                        requireActivity(),
                        "아이디 또는 비밀번호를 확인해주세요.",
                        R.style.toast_warning
                    ).show()
                }
            }
        }

        //회원가입 페이지 이동
        binding.btnSignup.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.register_fragment_container, SignupFragment())
                .addToBackStack(null)
                .commit()
        }

        return binding.root
    }


}