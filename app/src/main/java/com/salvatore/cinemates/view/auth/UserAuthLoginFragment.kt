package com.salvatore.cinemates.view.auth

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.salvatore.cinemates.MainActivity
import com.salvatore.cinemates.R
import com.salvatore.cinemates.databinding.UserAuthLoginFragmentBinding
import com.salvatore.cinemates.model.CinematesAuthUserDto
import com.salvatore.cinemates.model.CinematesUser
import com.salvatore.cinemates.network.NetworkApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class UserAuthLoginFragment: Fragment(R.layout.user_auth_login_fragment) {
    private var _binding: UserAuthLoginFragmentBinding? = null
    private val binding get() = _binding!!
    private val TAG = "UserAuthLoginFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = UserAuthLoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.binding.userLoginButton.setOnClickListener{
            if (this.binding.usernameEditText.text.isNullOrBlank() || this.binding.passwordEditText.text.isNullOrBlank()) {
                return@setOnClickListener
            } else {
                val userDto = CinematesAuthUserDto(this.binding.usernameEditText.text.toString(), this.binding.passwordEditText.text.toString())
                val loginResponseObservable = NetworkApiService.apiCall().performUserLogin(userDto)
                loginResponseObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            response -> run {
                                if (response.code() == 200 && response.body() != null) {
                                    (this.activity as MainActivity).saveUserInfoInSharedPrefs(response.body()!!)
                                    (this.activity as MainActivity).saveJwtTokenInPrefs(response.headers()["Authorization"].toString())
                                    val fragmentManager = (this.activity as MainActivity).supportFragmentManager
                                    val profileFragment = UserProfileFragment()
                                    val fragmentTransaction = fragmentManager.beginTransaction()
                                    fragmentTransaction.replace(R.id.fragmentsPlaceholderView, profileFragment)
                                    fragmentTransaction.commit()
                                } else if (response.code() == 403) {
                                    Log.d(TAG, "Unauthorized")
                                    //TODO: Aggiungere gestione visuale delle credenziali errate
                                }
                            }
                        },{
                            error -> Log.e(TAG, error.message!!)
                        })
            }
        }

        this.binding.userSignUpButton.setOnClickListener{
            val fragmentManager = (this.activity as MainActivity).supportFragmentManager
            val signupFragment = UserSignUpFragment()
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentsPlaceholderView, signupFragment)
                    .commit()
        }

        //TODO: make google login here

        //TODO: Make facebook login here
    }

}