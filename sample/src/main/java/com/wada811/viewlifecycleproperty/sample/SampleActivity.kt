package com.wada811.viewlifecycleproperty.sample

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.wada811.databinding.dataBinding
import com.wada811.viewlifecycleproperty.sample.databinding.SampleActivityBinding
import com.wada811.viewlifecycleproperty.sample.databinding.SampleListItemBinding
import com.wada811.viewlifecycleproperty.sample.sample1_val_by_lazy.Sample1Activity
import com.wada811.viewlifecycleproperty.sample.sample2_var_nullable.Sample2Activity
import com.wada811.viewlifecycleproperty.sample.sample3_lateinit_var.Sample3Activity
import com.wada811.viewlifecycleproperty.sample.sample4_auto_cleared.Sample4Activity
import com.wada811.viewlifecycleproperty.sample.sample5_view_lifecycle.Sample5Activity

class SampleActivity : AppCompatActivity(R.layout.sample_activity) {
    private val binding: SampleActivityBinding by dataBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Samples.values().forEach { sample ->
            val itemBinding = DataBindingUtil.inflate<SampleListItemBinding>(
                layoutInflater,
                R.layout.sample_list_item,
                binding.container,
                false
            )
            itemBinding.viewModel = sample
            itemBinding.root.setOnClickListener {
                startActivity(sample.createIntent(this@SampleActivity))
            }
            binding.container.addView(itemBinding.root)
        }
    }

    enum class Samples(private val clazz: Class<out Activity>) {
        Sample1(Sample1Activity::class.java) {
            override fun toString(): String = "val by lazy"
        },
        Sample2(Sample2Activity::class.java) {
            override fun toString(): String = "var nullable"
        },
        Sample3(Sample3Activity::class.java) {
            override fun toString(): String = "lateinit var"
        },
        Sample4(Sample4Activity::class.java) {
            override fun toString(): String = "AutoClearedValue"
        },
        Sample5(Sample5Activity::class.java) {
            override fun toString(): String = "ViewLifecycleProperty"
        },
        ;

        fun createIntent(context: Context) = Intent(context, clazz)
    }
}
