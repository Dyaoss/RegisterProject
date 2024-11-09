package com.dhproject.registerproject.presentation.register.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dhproject.registerproject.R
import com.dhproject.registerproject.data.RegisterViewModel
import com.dhproject.registerproject.databinding.FragmentSignupBinding
import com.dhproject.registerproject.presentation.register.signin.SigninFragment
import io.github.muddz.styleabletoast.StyleableToast

class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private val viewModel: RegisterViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)

        binding.btnSignupCheck.setOnClickListener {
            val userId = binding.etSignupId.text.toString()
            val userNickname = binding.etSignupNickname.text.toString()
            val userPassword = binding.etSignupPassword.text.toString()
            val userPasswordCheck = binding.etSignupPasswordCheck.text.toString()

            viewModel.registerUser(
                userId,
                userNickname,
                userPassword,
                userPasswordCheck
            ) { success ->
                if (success) {
                    requireActivity().supportFragmentManager.popBackStack()
                } else {
                    StyleableToast.makeText(requireContext(), "회원가입 실패", R.style.toast_warning)
                        .show()
                }
            }
        }

        binding.btnSignupCancel.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.register_fragment_container, SigninFragment())
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }

}