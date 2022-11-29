package com.binar.melif.presentation.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.binar.melif.base.BaseViewModelActivity
import com.binar.melif.databinding.ActivityLandingPageBinding
import com.binar.melif.databinding.ActivitySplashScreenBinding
import com.binar.melif.di.ServiceLocator
import com.binar.melif.presentation.ui.auth.AuthActivity
import com.binar.melif.presentation.ui.main.MainActivity
import com.binar.melif.presentation.ui.slider.LandingPageActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashScreenActivity : AppCompatActivity() {
    private val binding: ActivitySplashScreenBinding  by lazy {
        ActivitySplashScreenBinding.inflate(layoutInflater)
    }
    private var timer: CountDownTimer? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()


        setTimerSplashScreen()

    }

    override fun onDestroy() {
        super.onDestroy()

        if (timer != null) {
            timer?.cancel()
            timer = null
        }
    }
    private fun setTimerSplashScreen() {
        timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
//                if ( ServiceLocator.providePreferenceDataSource(this@SplashScreenActivity).isSkipIntro()){
//                    val intent = Intent(this@SplashScreenActivity, HomeActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                    startActivity(intent)
//                    finish()
//                }else{
                    val intent = Intent(this@SplashScreenActivity, LandingPageActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
//                }

            }
        }
        timer?.start()
    }


}