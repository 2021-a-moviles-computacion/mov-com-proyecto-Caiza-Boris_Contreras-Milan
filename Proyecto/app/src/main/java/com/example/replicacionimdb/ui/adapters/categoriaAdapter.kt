package com.example.replicacionimdb.ui.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.replicacionimdb.R
import com.example.replicacionimdb.ui.clases.Pelicula

class categoriaAdapter (

    val mList: ArrayList<String>,

): RecyclerView.Adapter<categoriaAdapter.ViewPagerViewHolder>(){
    inner class ViewPagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_categoria,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val curmList = mList[position]

        val categoria = holder.itemView.findViewById<TextView>(R.id.TV_categoria_item)
        categoria.text = curmList

    }
}