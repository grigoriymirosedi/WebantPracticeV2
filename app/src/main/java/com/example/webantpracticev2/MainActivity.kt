package com.example.webantpracticev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.window.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                //TODO: replace true with Handler?
                true
            }
        }
        setContentView(R.layout.activity_main)
    }
}