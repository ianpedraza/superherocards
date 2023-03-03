package com.ianpedraza.collectiblecards.ui.common

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver

open class BaseLifecycleObserverActivity(name: String) : AppCompatActivity() {
    private val lifecycleObserver: DefaultLifecycleObserver = BaseLifecycleObserver(name)

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(lifecycleObserver)
    }
}
