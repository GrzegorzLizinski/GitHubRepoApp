package com.example.githubrepoapp.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.githubrepoapp.R
import com.example.githubrepoapp.views.fragments.SplashFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startMainFragment()
        supportActionBar?.hide()
    }

    private fun startMainFragment() {
        val newFragment: Fragment = SplashFragment.newInstance()
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}