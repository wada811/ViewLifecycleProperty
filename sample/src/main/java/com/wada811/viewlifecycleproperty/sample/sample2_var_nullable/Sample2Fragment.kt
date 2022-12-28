package com.wada811.viewlifecycleproperty.sample.sample2_var_nullable

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.wada811.databinding.withBinding
import com.wada811.viewlifecycleproperty.sample.R
import com.wada811.viewlifecycleproperty.sample.SampleProperty
import com.wada811.viewlifecycleproperty.sample.databinding.BackStackFragmentBinding

class Sample2Fragment : Fragment(R.layout.back_stack_fragment) {
    private var property: SampleProperty? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        withBinding<BackStackFragmentBinding> { binding ->
            binding.text = requireArguments().getString(EXTRA_TEXT)
        }
        property = SampleProperty(viewLifecycleOwner.lifecycle, requireContext(), R.string.app_name)
        Log.d("ViewLifecycleProperty", "property.log: ${property!!.log}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("ViewLifecycleProperty", "property.log: ${property!!.log}")
        property = null
    }

    companion object {
        private const val EXTRA_TEXT = "text"
        fun newInstance(text: String) = Sample2Fragment().also { fragment ->
            fragment.arguments = Bundle().also { bundle ->
                bundle.putString(EXTRA_TEXT, text)
            }
        }
    }
}
