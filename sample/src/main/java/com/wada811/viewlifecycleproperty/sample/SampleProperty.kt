package com.wada811.viewlifecycleproperty.sample

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class SampleProperty(lifecycle: Lifecycle, context: Context, resId: Int) {
    private val text = context.getString(resId).repeat(1000000)
    var log: String = ""

    init {
        lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                log += event.name + ", "
            }
        })
    }
}