package com.wada811.viewlifecycleproperty.sample.sample5_view_lifecycle

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.wada811.databinding.dataBinding
import com.wada811.viewlifecycleproperty.sample.R
import com.wada811.viewlifecycleproperty.sample.SampleProperty
import com.wada811.viewlifecycleproperty.sample.databinding.BackStackFragmentBinding
import com.wada811.viewlifecycleproperty.viewLifecycle

class Sample5Fragment : Fragment(R.layout.back_stack_fragment) {
    private val binding: BackStackFragmentBinding by dataBinding()
    private val property: SampleProperty by viewLifecycle {
        SampleProperty(
            viewLifecycleOwner.lifecycle,
            requireContext(),
            R.string.app_name
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.text = requireArguments().getString(EXTRA_TEXT)
        Log.d("ViewLifecycleProperty", "property.log: ${property.log}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("ViewLifecycleProperty", "property.log: ${property.log}")
    }

    companion object {
        private const val EXTRA_TEXT = "text"
        fun newInstance(text: String) = Sample5Fragment().also { fragment ->
            fragment.arguments = Bundle().also { bundle ->
                bundle.putString(EXTRA_TEXT, text)
            }
        }
    }
}
