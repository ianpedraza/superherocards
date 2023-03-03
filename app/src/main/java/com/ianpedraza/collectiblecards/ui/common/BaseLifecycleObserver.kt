package com.ianpedraza.collectiblecards.ui.common

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class BaseLifecycleObserver(private val name: String) : DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        logStatus("onCreate", owner)
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        logStatus("onStart", owner)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        logStatus("onResume", owner)
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        logStatus("onPause", owner)
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        logStatus("onStop", owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        logStatus("onDestroy", owner)
    }

    private fun logStatus(stage: String, owner: LifecycleOwner) {
        Log.d(name, "BaseLifecycleObserver:$stage: ${owner.lifecycle.currentState}")
    }
}
