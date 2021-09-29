package com.example.replicacionimdb.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.replicacionimdb.R
import com.example.replicacionimdb.ui.clases.PeliculaSearch
import com.example.replicacionimdb.ui.clases.Persona

class PeliculaSearchItemAdapter (
    val mList: List<PeliculaSearch>,

    ): RecyclerView.Adapter<PeliculaSearchItemAdapter.ViewPagerViewHolder>(){
    inner class ViewPagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_buscar,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val curmList = mList[position]

        val imagen =  holder.itemView.findViewById<ImageView>(R.id.IV_imageSearch)
        imagen.setImageResource(curmList.Imagen)


        val texto =  holder.itemView.findViewById<TextView>(R.id.TV_textoSearch)
        texto.text = curmList.Texto




    }
}