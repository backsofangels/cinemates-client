package com.salvatore.cinemates.view.auth

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fasterxml.jackson.databind.ObjectMapper
import com.salvatore.cinemates.R
import com.salvatore.cinemates.databinding.UserProfileFragmentBinding
import com.salvatore.cinemates.model.CinematesUser
import java.io.IOException

class UserProfileFragment: Fragment(R.layout.user_profile_fragment) {
    private var _binding: UserProfileFragmentBinding? = null
    private val binding get() = _binding!!
    private var userInfo: CinematesUser? = null
    private val TAG = "UserProfileFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = UserProfileFragmentBinding.inflate(inflater, container, false)
        userInfo = retrieveUserInfoFromSharedPrefs()
        this.binding.usernameLabel.text = userInfo?.username
        return binding.root
    }


    @Throws(IOException::class)
    fun retrieveUserInfoFromSharedPrefs(): CinematesUser {
        val sharedPrefs = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val jsonMapper = ObjectMapper()
        return jsonMapper.readValue(sharedPrefs.getString("userInfo", ""), CinematesUser::class.java)
    }
}