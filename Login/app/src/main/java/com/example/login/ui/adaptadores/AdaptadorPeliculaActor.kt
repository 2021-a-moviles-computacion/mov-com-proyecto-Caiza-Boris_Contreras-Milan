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
import com.example.login.ui.clases.PeliculaFireBase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class AdaptadorPeliculaActor(private var lista: ArrayList<PeliculaFireBase>, val contexto: Context) : RecyclerView.Adapter<AdaptadorPeliculaActor.ViewHolder>() {


    inner class ViewHolder( var vista: View) : RecyclerView.ViewHolder(vista) {

        var imagen: ImageView
        val calificacion: TextView
        var titulo: TextView
        var ano:TextView
        var clasificacion: TextView
        var duracion: TextView



        init {
            imagen = vista.findViewById(R.id.dapIvImagen)
            calificacion = vista.findViewById(R.id.ipaTvCalificacion)
            titulo = vista.findViewById(R.id.ipaTvTitle)
            ano =  vista.findViewById(R.id.ipaTvAnio)
            clasificacion =  vista.findViewById(R.id.ipaTvCalasificacion)
            duracion = vista.findViewById(R.id.ipaTvDuracion)




        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pelicula_actor,parent,false)
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
        holder.titulo.text = pelicula.nombre.toString()
        holder.ano.text = pelicula.ano.toString()
        holder.clasificacion.text = pelicula.clasificacion.toString()
        holder.duracion.text = pelicula.duracion.toString()

        //////Imagen
        holder.imagen.setOnClickListener{
            contexto.startActivity(Intent(contexto, DetallePelicula::class.java).putExtra("pel",pelicula) )

        }



    }



}