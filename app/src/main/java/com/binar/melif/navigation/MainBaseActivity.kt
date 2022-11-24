package com.binar.melif.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.binar.melif.R
import com.binar.melif.base.BaseActivity
import com.binar.melif.databinding.ActivityMainBaseBinding
import com.binar.melif.navigation.fragment.FirstFragment
import com.binar.melif.navigation.fragment.SecondFragment

class MainBaseActivity : BaseActivity<ActivityMainBaseBinding>(ActivityMainBaseBinding::inflate){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()
//        val threadActivity = Intent(this@MainBaseActivity, ThreadActivity::class.java)

        setCurrentFragment(firstFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.movie -> setCurrentFragment(firstFragment)
                R.id.tvshow -> setCurrentFragment(secondFragment)
//                R.id.chat -> startActivity(threadActivity)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_fragment, fragment)
            commit()
        }
    }

}