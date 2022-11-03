package com.cre.crossselectronics.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.cre.crossselectronics.R
import com.cre.crossselectronics.model.SliderModel

class SliderAdapter(
    private var list:ArrayList<SliderModel>,
    private var context: Context
    ) :RecyclerView.Adapter<SliderAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val sliderImage:ImageView= itemView.findViewById(R.id.slider_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.slider_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.sliderImage.setImageResource(currentItem.sliderImage)

        holder.itemView.setOnClickListener {

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

}