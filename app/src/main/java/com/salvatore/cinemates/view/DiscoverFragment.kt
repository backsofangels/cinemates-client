package com.salvatore.cinemates.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.salvatore.cinemates.R
import com.salvatore.cinemates.databinding.FragmentDiscoverBinding

class DiscoverFragment: Fragment(R.layout.fragment_discover) {
    private var _binding: FragmentDiscoverBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.binding.discoverFragmentDiscoverMoviesTextView.text = "TESTPROVASA WAAAAAAAAAAA"
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}