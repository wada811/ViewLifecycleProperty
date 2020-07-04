package com.wada811.viewlifecycleproperty.sample.sample4_auto_cleared

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.wada811.databinding.dataBinding
import com.wada811.viewlifecycleproperty.sample.R
import com.wada811.viewlifecycleproperty.sample.SampleProperty
import com.wada811.viewlifecycleproperty.sample.databinding.BackStackFragmentBinding

class Sample4Fragment : Fragment(R.layout.back_stack_fragment) {
    private val binding: BackStackFragmentBinding by dataBinding()
    private var property: SampleProperty by autoCleared()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.text = requireArguments().getString(EXTRA_TEXT)
        property = SampleProperty(viewLifecycleOwner.lifecycle, requireContext(), R.string.app_name)
        Log.d("ViewLifecycleProperty", "property.log: ${property.log}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        try {
            Log.d("ViewLifecycleProperty", "property.log: ${property.log}")
        } catch (e: IllegalStateException) {
            Log.e("ViewLifecycleProperty", "Can't access property in onDestroyView", e)
        }
    }

    companion object {
        private const val EXTRA_TEXT = "text"
        fun newInstance(text: String) = Sample4Fragment().also { fragment ->
            fragment.arguments = Bundle().also { bundle ->
                bundle.putString(EXTRA_TEXT, text)
            }
        }
    }
}
