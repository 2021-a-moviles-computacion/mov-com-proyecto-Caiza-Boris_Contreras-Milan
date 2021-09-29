package com.example.examens2.ui.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.examens2.R
import com.example.examens2.ui.clases.Persona


class PersonaAdapter (
    val mList: List<Persona>,

    ): RecyclerView.Adapter<PersonaAdapter.ViewPagerViewHolder>(){
    inner class ViewPagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_persona,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val curmList = mList[position]

        val fotoActor =  holder.itemView.findViewById<ImageView>(R.id.Iv_personaImagen)
        fotoActor.setImageResource(curmList.ImagePersona)


        val nombreActor =  holder.itemView.findViewById<TextView>(R.id.Tv_nombre_persona)
        nombreActor.text = curmList.Nombre

        val nombrePersonaje =  holder.itemView.findViewById<TextView>(R.id.tv_nombre_persona_pelicula)
        nombrePersonaje.text = curmList.NombrePersonaje


    }
}