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

class PeliculaAdapter (

    val mList: List<Pelicula>,
    val parentFragment: NavController

): RecyclerView.Adapter<PeliculaAdapter.ViewPagerViewHolder>(){
    inner class ViewPagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pelicula,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val curmList = mList[position]

        val portadaImg =  holder.itemView.findViewById<ImageView>(R.id.Iv_pelicula_portada)
        portadaImg.setImageResource(curmList.ImagePortada)


        val calificacionPelicula =  holder.itemView.findViewById<TextView>(R.id.Tv_nombre_persona)
        calificacionPelicula.text = curmList.Calificacion

        val tituloPelicula =  holder.itemView.findViewById<TextView>(R.id.Tv_nombre_persona_pelicula)
        tituloPelicula.text = curmList.Title

        val infoPelicula =  holder.itemView.findViewById<TextView>(R.id.Tv_informacion)
        infoPelicula.text = curmList.Descripcion

        val pelicula =  holder.itemView.findViewById<ConstraintLayout>(R.id.constraint_pelicula)

        pelicula.setOnClickListener{
            var bundle = Bundle()
            bundle.putString("Titulo",curmList.Title)
            bundle.putString("Descripcion",curmList.Descripcion)
            bundle.putInt("Portada",curmList.ImagePortada)
            bundle.putInt("Trailer",curmList.ImageTrailer)
            bundle.putString("Sinopsis",curmList.Sinopsis)
            bundle.putString("Director",curmList.Director)
            bundle.putString("Guionistas",curmList.Guionistas)
            bundle.putString("Ano",curmList.Ano)
            bundle.putString("Calificacion",curmList.Calificacion)

            bundle.putStringArrayList("Categorias",curmList.Categorias)
            bundle.putParcelableArrayList("Reparto",curmList.Actores)
            parentFragment.navigate(R.id.navigation_actividadDetallePelicula,bundle)
        }


    }
}