package com.cre.crossselectronics.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.cre.crossselectronics.R
import com.cre.crossselectronics.databinding.ActivityAskBinding

class AskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {

            binding.imageView2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.am_smallbigforth))
            binding.imageView2.animate().alpha(0f).duration = 1700

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, GameActivity::class.java)
                startActivity(intent)
                finish()
            }, 1750)
        }

        binding.btnNo.setOnClickListener {

            binding.imageView2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.am_smallbigforth))
            binding.imageView2.animate().alpha(0f).duration = 1700

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, SlideActivity::class.java)
                startActivity(intent)
                finish()
            }, 1750)
        }

    }
}

