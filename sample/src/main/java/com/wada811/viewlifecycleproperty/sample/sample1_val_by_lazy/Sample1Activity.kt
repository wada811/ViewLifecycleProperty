package com.wada811.viewlifecycleproperty.sample.sample1_val_by_lazy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wada811.databinding.withBinding
import com.wada811.viewlifecycleproperty.sample.R
import com.wada811.viewlifecycleproperty.sample.databinding.BackStackActivityBinding

class Sample1Activity : AppCompatActivity(R.layout.back_stack_activity) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        withBinding<BackStackActivityBinding> { binding ->
            binding.addButton.setOnClickListener {
                val text = "${supportFragmentManager.backStackEntryCount + 1}"
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment, Sample1Fragment.newInstance(text), text)
                    .addToBackStack(text)
                    .commit()
            }
            binding.popButton.setOnClickListener {
                supportFragmentManager.popBackStack()
            }
        }
        if (savedInstanceState == null) {
            val text = "${supportFragmentManager.backStackEntryCount}"
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment, Sample1Fragment.newInstance(text), text)
                .commit()
        }
    }
}
