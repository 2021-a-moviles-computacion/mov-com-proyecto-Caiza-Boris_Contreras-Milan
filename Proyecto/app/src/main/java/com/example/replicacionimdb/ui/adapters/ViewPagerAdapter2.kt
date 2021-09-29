package com.example.replicacionimdb.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.replicacionimdb.R
import com.example.replicacionimdb.ui.clases.Slide

class ViewPagerAdapter2 (
    val mList: List<Slide>
): RecyclerView.Adapter<ViewPagerAdapter2.ViewPagerViewHolder>(){
    inner class ViewPagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slide_item,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        var navController: NavController? = null

        val curmList = mList[position]

        val slideImg =  holder.itemView.findViewById<ImageView>(R.id.slide_img)
        slideImg.setImageResource(curmList.ImageTrailer)

        val slideImg2 =  holder.itemView.findViewById<ImageView>(R.id.slide_img2)
        slideImg2.setImageResource(curmList.ImagePortada)

        val slideText =  holder.itemView.findViewById<TextView>(R.id.slide_title)
        slideText.text = curmList.Title

        val slideDescripcion =  holder.itemView.findViewById<TextView>(R.id.slide_descripcion)
        slideDescripcion.text = curmList.Descripcion









    }

    override fun getItemCount(): Int {
        return mList.size
    }

}