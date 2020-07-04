/*
 * Copyright (C) 2020 wada811
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wada811.viewlifecycleproperty

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * This method is extension method returning delegated property `ViewLifecycleProperty`.
 *
 * ViewLifecycleProperty is
 *  - lazy initialization
 *  - accessible when Fragment's view isn't null
 *  - automatically cleared by null when Fragment's view released
 */
fun <T> Fragment.viewLifecycle(initialize: (() -> T)? = null) = ViewLifecycleProperty(this, initialize)

class ViewLifecycleProperty<T>(private val fragment: Fragment, private val initialize: (() -> T)?) : ReadWriteProperty<Fragment, T> {
    private var value: T? = null

    init {
        // viewLifecycleOwnerLiveData set value after onCreateView
        fragment.viewLifecycleOwnerLiveData.observe(fragment, object : Observer<LifecycleOwner> {
            override fun onChanged(viewLifecycleOwner: LifecycleOwner?) {
                // onChanged called when STARTED
                fragment.viewLifecycleOwnerLiveData.removeObserver(this)
                fragment.viewLifecycleOwnerLiveData.observeForever(object : Observer<LifecycleOwner> {
                    override fun onChanged(owner: LifecycleOwner?) {
                        if (owner == null) {
                            // after onDestroyView, viewLifecycleOwnerLiveData set null in FragmentManagerImpl
                            fragment.viewLifecycleOwnerLiveData.removeObserver(this)
                            value = null
                        }
                    }
                })
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        checkNotNull(fragment.viewLifecycleOwnerLiveData.value) {
            "Can't access the Fragment[${thisRef.tag}]'s property[${property.name}] when getView() is null i.e., before onCreateView() or after onDestroyView()"
        }
        return value ?: initialize?.invoke()?.also { value = it } ?: throw IllegalStateException("Fragment[${thisRef.tag}]'s property[${property.name}] is not initialized.")
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        this.value = value
    }
}
