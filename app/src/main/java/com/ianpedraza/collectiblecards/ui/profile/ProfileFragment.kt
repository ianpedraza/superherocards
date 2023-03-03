package com.ianpedraza.collectiblecards.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ianpedraza.collectiblecards.databinding.FragmentProfileBinding
import com.ianpedraza.collectiblecards.ui.common.BaseLifecycleObserverFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseLifecycleObserverFragment(TAG) {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        private const val TAG = "ProfileFragment"
    }
}
