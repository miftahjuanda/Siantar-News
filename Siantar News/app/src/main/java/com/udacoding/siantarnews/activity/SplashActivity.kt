package com.udacoding.siantarnews.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.AnticipateOvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.udacoding.siantarnews.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    private var animation: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable {
            startActivity(Intent(this, MainActivity::class.java))
        }, 2100)

        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.animation_splash)
        animation?.setInterpolator(AnticipateOvershootInterpolator())
        animation?.setDuration(2000)
        img_splash.startAnimation(animation)
    }

    override fun onBackPressed() {
        finishAffinity()
        finish()
    }
}

