package com.cre.crossselectronics.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.cre.crossselectronics.R
import com.cre.crossselectronics.databinding.ActivityCategoriesBinding

class CategoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriesBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_categories)

        val categoryId = intent.getStringExtra("categoryId").toString()
        val layoutManager = GridLayoutManager(this, 2)

        binding.categoryRv.layoutManager = layoutManager
        binding.categoryRv.showShimmerAdapter()

//        when (categoryId) {
//            "1" -> {
////                val intent = Intent(this, GameActivity::class.java)
////                startActivity(intent)
////                finish()
//            }
//
//            "2" -> {
////                val intent = Intent(this, SplashActivity::class.java)
////                startActivity(intent)
////                finish()
//            }
//
//            "3" -> {
////                val intent = Intent(this, SplashActivity::class.java)
////                startActivity(intent)
////                finish()
//            }
//
//        }

    }
}



