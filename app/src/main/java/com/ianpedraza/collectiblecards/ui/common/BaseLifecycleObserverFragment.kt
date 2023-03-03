package com.ianpedraza.collectiblecards.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver

open class BaseLifecycleObserverFragment(name: String) : Fragment() {
    private val lifecycleObserver: DefaultLifecycleObserver = BaseLifecycleObserver(name)

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewLifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
