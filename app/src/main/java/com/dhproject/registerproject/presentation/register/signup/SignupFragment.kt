package com.dhproject.registerproject.presentation.register.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dhproject.registerproject.MyApp
import com.dhproject.registerproject.R
import com.dhproject.registerproject.data.RegisterViewModel
import com.dhproject.registerproject.data.RegisterViewModelFactory
import com.dhproject.registerproject.databinding.FragmentSignupBinding
import com.dhproject.registerproject.presentation.register.signin.SigninFragment
import io.github.muddz.styleabletoast.StyleableToast

class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private val viewModel: RegisterViewModel by activityViewModels {
        RegisterViewModelFactory((requireActivity().application as MyApp).userRepository)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)

        binding.btnSignupCheck.setOnClickListener {
            val userId = binding.etSignupId.text.toString().trim()
            val userNickname = binding.etSignupNickname.text.toString().trim()
            val userPassword = binding.etSignupPassword.text.toString().trim()
            val userPasswordCheck = binding.etSignupPasswordCheck.text.toString().trim()

            //유효성 검사
            if (validateInput(userId, userNickname, userPassword, userPasswordCheck)) {

                viewModel.registerUser(
                    userId,
                    userNickname,
                    userPassword,
                    userPasswordCheck
                ) { success ->
                    if (success) {
                        StyleableToast.makeText(requireContext(), "회원가입 성공", R.style.toast_warning)
                            .show()
                        goToSigninFragment()
                    } else {
                        StyleableToast.makeText(requireContext(), "회원가입 실패", R.style.toast_warning)
                            .show()
                    }
                }
            }
        }

        binding.btnSignupCancel.setOnClickListener {
            goToSigninFragment()
        }
        return binding.root
    }

    private fun validateInput(
        userId: String,
        userNickname: String,
        userPassword: String,
        userPasswordCheck: String
    ): Boolean {
        return when {
            userId.isEmpty() -> {
                StyleableToast.makeText(requireContext(), "아이디를 입력하세요.", R.style.toast_warning)
                    .show()
                false
            }

            userNickname.isEmpty() -> {
                StyleableToast.makeText(requireContext(), "닉네임을 입력하세요.", R.style.toast_warning)
                    .show()
                false
            }

            userPassword.isEmpty() -> {
                StyleableToast.makeText(requireContext(), "비밀번호를 입력하세요.", R.style.toast_warning)
                    .show()
                false
            }

            userPassword != userPasswordCheck -> {
                StyleableToast.makeText(requireContext(), "비밀번호가 일치하지 않습니다.", R.style.toast_warning)
                    .show()
                false
            }

            userPassword.length < 6 -> {
                StyleableToast.makeText(
                    requireContext(),
                    "비밀번호는 최소 6자리 이상이어야 합니다.",
                    R.style.toast_warning
                ).show()
                false
            }

            else -> true
        }
    }

    private fun goToSigninFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.register_fragment_container, SigninFragment())
            .addToBackStack(null)
            .commit()

    }

}