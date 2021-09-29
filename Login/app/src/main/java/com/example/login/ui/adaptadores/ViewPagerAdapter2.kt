package com.example.login.ui.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.ui.clases.Slide

class ViewPagerAdapter2 (
    val mList: List<Slide>, Context: Context
): RecyclerView.Adapter<ViewPagerAdapter2.ViewPagerViewHolder>(){
    inner class ViewPagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slide_item,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        var navController: NavController? = null

        val curmList = mList[position]



        val slideImg2 =  holder.itemView.findViewById<ImageView>(R.id.slide_img2)
        slideImg2.setImageResource(curmList.imagenPortada)

    }

    override fun getItemCount(): Int {
        return mList.size
    }

}