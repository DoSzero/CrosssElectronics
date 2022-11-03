package com.cre.crossselectronics.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.cre.crossselectronics.adapters.SliderAdapter
import com.cre.crossselectronics.R
import com.cre.crossselectronics.databinding.ActivitySlideBinding
import com.cre.crossselectronics.model.SliderModel
import kotlin.math.abs
import kotlin.system.exitProcess

class SlideActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySlideBinding

    private lateinit var sliderAdapter: SliderAdapter
    lateinit var isList:ArrayList<SliderModel>

    private var backPressedTime: Long = 0
    lateinit var sliderHandler: Handler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= DataBindingUtil.setContentView(this, R.layout.activity_slide)

        isList = ArrayList()
        sliderHandler = Handler()
        sliderAdapter = SliderAdapter(isList,this)

//        GlobalScope.launch {
            binding.apply {
                isList.add(SliderModel("1", R.drawable.slide_1))
                isList.add(SliderModel("2", R.drawable.slide_2))
                isList.add(SliderModel("3", R.drawable.slide_3))

                sliderAdapter.notifyDataSetChanged()

                isViewImage.adapter = sliderAdapter
                isViewImage.clipChildren = false
                isViewImage.clipToPadding = false
                isViewImage.offscreenPageLimit = 3

                isViewImage.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
                val compositePageTransformer = CompositePageTransformer()

                compositePageTransformer.addTransformer(MarginPageTransformer(40))

                compositePageTransformer.addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = 0.85f + r * 0.15f
                }

                isViewImage.setPageTransformer(compositePageTransformer)
                isViewImage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                        sliderHandler.removeCallbacks(sliderRunnable)
                        sliderHandler.postDelayed(sliderRunnable, 5000)
                        if (position == isList.size - 2) {
                          isViewImage.post(runnable)
                        }
                    }
                })
            }
//        }

        binding.btnPlay.setOnClickListener {
            val intent= Intent(this, AskActivity::class.java)
            startActivity(intent)
        }

        binding.btnSounds.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Звук")
                .setMessage("Настройки Звука будут доступны в следящем обновлении !")
                .setNegativeButton("Ок", null)
                .show()
        }


        binding.btnAbout.setOnClickListener {
            val intent= Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }

        binding.btnExit.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed({
                finish()
                exitProcess(0);
            }, 900)
        }

    }

    val sliderRunnable = Runnable {
        binding.isViewImage.currentItem = binding.isViewImage.currentItem +1
    }

    @SuppressLint("NotifyDataSetChanged")
    val runnable = Runnable {
        isList.addAll(isList)
        sliderAdapter.notifyDataSetChanged()
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRunnable)

    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable,5000)
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishAffinity()
        } else {
            Snackbar.make(
                this.findViewById(android.R.id.content),
                "Дважды нажмите, чтобы выйти",
                Snackbar.LENGTH_LONG
            ).show()
        }
        backPressedTime = System.currentTimeMillis()
    }

}