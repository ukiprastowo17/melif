package com.binar.melif.presentation.ui.splashscreen

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.binar.melif.databinding.ActivitySplashScreenBinding
import com.binar.melif.presentation.ui.slider.LandingPageActivity


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

        try {
            val pInfo: PackageInfo = packageManager.getPackageInfo(packageName, 0)
            val version = pInfo.versionName
            binding.tvVersi.text = "Versi App : $version"
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }



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