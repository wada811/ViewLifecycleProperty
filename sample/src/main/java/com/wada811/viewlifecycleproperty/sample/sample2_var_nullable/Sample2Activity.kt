package com.wada811.viewlifecycleproperty.sample.sample2_var_nullable

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wada811.databinding.dataBinding
import com.wada811.viewlifecycleproperty.sample.R
import com.wada811.viewlifecycleproperty.sample.databinding.BackStackActivityBinding

class Sample2Activity : AppCompatActivity(R.layout.back_stack_activity) {
    private val binding: BackStackActivityBinding by dataBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.addButton.setOnClickListener {
            val text = "${supportFragmentManager.backStackEntryCount + 1}"
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, Sample2Fragment.newInstance(text), text)
                .addToBackStack(text)
                .commit()
        }
        binding.popButton.setOnClickListener {
            supportFragmentManager.popBackStack()
        }
        if (savedInstanceState == null) {
            val text = "${supportFragmentManager.backStackEntryCount}"
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment, Sample2Fragment.newInstance(text), text)
                .commit()
        }
    }
}
