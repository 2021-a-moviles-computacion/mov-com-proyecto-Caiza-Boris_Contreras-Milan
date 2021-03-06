package com.example.login.ui.adaptadores

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.login.DetallePelicula
import com.example.login.R
import com.example.login.ui.clases.Pelicula
import com.example.login.ui.clases.PeliculaFireBase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class AdaptadorPelicula(private var lista: ArrayList<PeliculaFireBase>, val contexto: Context) : RecyclerView.Adapter<AdaptadorPelicula.ViewHolder>() {


    inner class ViewHolder( var vista: View) : RecyclerView.ViewHolder(vista) {

        var imagen: ImageView
        val calificacion: TextView



        init {
            imagen = vista.findViewById(R.id.elpIvPelicula)
            calificacion = vista.findViewById(R.id.tv_calificacion)




        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pelicula,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pelicula = lista[position]

        val storage = Firebase.storage
        var imagenRef = storage.getReferenceFromUrl(pelicula.portadaLink.toString())


        Glide.with(contexto)
            .load(imagenRef)
            .into(holder.imagen)


        //holder.imagen.setImageResource(pelicula.ImagePortada)
        holder.calificacion.text = pelicula.calificacion.toString()

        //////Imagen
        holder.imagen.setOnClickListener{
            contexto.startActivity(Intent(contexto, DetallePelicula::class.java).putExtra("pel",pelicula) )

        }



    }



}