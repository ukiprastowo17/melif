package com.binar.melif.presentation.ui.slider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.binar.melif.R
import com.binar.melif.databinding.ActivityLandingPageBinding
import com.binar.melif.di.ServiceLocator
import com.binar.melif.presentation.ui.slider.model.SliderData
import com.binar.melif.presentation.ui.slider.utils.ViewPagerAdapter
import com.binar.projectgroupmakerbinar.ui.slider.utils.getNextIndex
import com.binar.projectgroupmakerbinar.ui.slider.utils.getPreviousIndex


class LandingPageActivity : AppCompatActivity() {
    private val binding: ActivityLandingPageBinding by lazy {
        ActivityLandingPageBinding.inflate(layoutInflater)
    }

    private val pagerAdapter: ViewPagerAdapter by lazy {
        ViewPagerAdapter(supportFragmentManager, lifecycle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        initFragmentViewPager()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.tvNext.setOnClickListener {
            navigateToNextFragment()
        }
        binding.tvBack.setOnClickListener {
            navigateToBackFragment()
        }

//        binding.tvMenu.setOnClickListener {
//            navigateToMenuFragment()
//        }


    }

    private fun navigateToBackFragment() {
        val nextIndex = binding.vpIntro.getPreviousIndex()
        if (nextIndex != -1) {
            binding.vpIntro.setCurrentItem(nextIndex, true)
        }
    }

    private fun navigateToNextFragment() {
        val nextIndex = binding.vpIntro.getNextIndex()
        if (nextIndex != -1) {
            binding.vpIntro.setCurrentItem(nextIndex, true)
        }
    }

//    private fun navigateToMenuFragment(){
//        ServiceLocator.providePreferenceDataSource(this@LandingPageActivity).setSkipIntro(true)
//        val i = Intent(this@LandingPageActivity, HomeActivity::class.java)
//        startActivity(i)
//        finish()
//    }

    private fun initFragmentViewPager() {
        initAdapter()
        setupViewPager()
    }

    private fun initAdapter() {
        pagerAdapter.apply {
            addFragment(
                SliderFragment.newInstance(
                    SliderData(
                        "Easy \nTo Use",
                        "Membuat grup menjadi lebih mudah \nhanya dari ponsel Anda.",
                        R.drawable.lp01
                    )
                )
            )
            addFragment(
                SliderFragment.newInstance(
                    SliderData(
                        "Fast & \nBest Solution",
                        "Sebagai sebuah solusi yang cepat dan tepat. Anda juga bisa memberikan nama grup.",
                        R.drawable.lp02
                    )
                )
            )
        }
    }

    private fun setupViewPager() {
        binding.vpIntro.apply {
            adapter = pagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when {
                        position == 0 -> {
                            binding.tvNext.isInvisible = false
                            binding.tvNext.isEnabled = true
                            binding.tvBack.isInvisible = true
                            binding.tvBack.isEnabled = false
//                            binding.tvMenu.isGone = true
                        }
                        position < pagerAdapter.getMaxIndex() -> {
                            binding.tvNext.isInvisible = false
                            binding.tvNext.isEnabled = true
                            binding.tvBack.isInvisible = false
                            binding.tvBack.isEnabled = true
//                            binding.tvMenu.isGone = true
                        }
                        position == pagerAdapter.getMaxIndex() -> {
                            binding.tvNext.isInvisible = true
                            binding.tvNext.isEnabled = false
                            binding.tvBack.isInvisible = false
                            binding.tvBack.isEnabled = true
//                            binding.tvMenu.isVisible = true
                        }
                    }
                }
            })
        }
        binding.dotsIndicator.attachTo(binding.vpIntro)
    }


}