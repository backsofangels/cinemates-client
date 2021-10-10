package com.salvatore.cinemates.view.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.salvatore.cinemates.MainActivity
import com.salvatore.cinemates.R
import com.salvatore.cinemates.databinding.UserAuthSignupFragmentBinding
import com.salvatore.cinemates.model.CinematesUser
import com.salvatore.cinemates.network.NetworkApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class UserSignUpFragment: Fragment(R.layout.user_auth_signup_fragment) {
    private var _binding: UserAuthSignupFragmentBinding? = null
    private val binding get() = _binding!!
    private var isUsernameAvailable: Boolean = false
    private var doPasswordMatch: Boolean = false
    private val TAG = "UserSignupFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = UserAuthSignupFragmentBinding.inflate(inflater, container, false)
        this.binding.usernameSignUpEdit.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus && !this.binding.usernameSignUpEdit.text.isNullOrBlank() && !isUsernameAvailable) {
                Log.d(TAG, "Check username availability")
                isUsernameAvailable = true
                //(this.activity as MainActivity).hideKeyboard(this.activity as MainActivity)
            }
        }

        this.binding.secondPasswordEditText.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus
                    && !doPasswordMatch
                    && this.binding.firstPasswordEditText.text.toString().isNotEmpty()
                    && this.binding.secondPasswordEditText.text.toString().isNotEmpty()) {
                if (this.binding.firstPasswordEditText.text.toString() == this.binding.secondPasswordEditText.text.toString()) {
                    doPasswordMatch = true
                    Log.d(TAG, "Password match")
                } else {
                    Log.d(TAG, "Password do not match")
                }
            }
        }

        this.binding.signUpButton.setOnClickListener {
            if (isUsernameAvailable && doPasswordMatch) {
                val cinematesUser = CinematesUser()
                cinematesUser.username = this.binding.usernameSignUpEdit.text.toString()
                cinematesUser.password = this.binding.firstPasswordEditText.text.toString()
                val userSignUpResponseObservable = NetworkApiService.apiCall().performUserSignup(cinematesUser)
                userSignUpResponseObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                                   
                        },{})
            } else {
                Log.d(TAG, "isUsernameAvailable: $isUsernameAvailable\ndoPasswordMatch: $doPasswordMatch")
            }
        }

        return binding.root
    }
    
}