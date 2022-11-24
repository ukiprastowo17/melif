package com.binar.melif.navigation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.binar.melif.R
import com.binar.melif.SampleAboutActivity
import com.binar.melif.base.BaseActivity
import com.binar.melif.databinding.ActivityMainBaseBinding
import com.binar.melif.navigation.fragment.FirstFragment
import com.binar.melif.navigation.fragment.SecondFragment
import com.google.android.material.appbar.AppBarLayout

class MainBaseActivity : BaseActivity<ActivityMainBaseBinding>(ActivityMainBaseBinding::inflate){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbarMenu)
        supportActionBar?.apply {
            setDisplayShowCustomEnabled(true)
        }

        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()
//        val threadActivity = Intent(this@MainBaseActivity, ThreadActivity::class.java)

        setCurrentFragment(firstFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.item_movie -> setCurrentFragment(firstFragment)
                R.id.item_tvshow -> setCurrentFragment(secondFragment)
//                R.id.item_chat -> startActivity(threadActivity)
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.about_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Todo: Navigate to About Activity
        val aboutActivity = Intent(this@MainBaseActivity, SampleAboutActivity::class.java)
        when(item.itemId){
            R.id.item_about -> startActivity(aboutActivity)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_fragment, fragment)
            commit()
        }
    }

}