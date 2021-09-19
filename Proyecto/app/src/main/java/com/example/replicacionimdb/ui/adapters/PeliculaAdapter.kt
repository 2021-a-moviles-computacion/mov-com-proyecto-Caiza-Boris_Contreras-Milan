package com.example.replicacionimdb.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.replicacionimdb.DetallePelicula
import com.example.replicacionimdb.R
import com.example.replicacionimdb.ui.clases.Pelicula


class PeliculaAdapter(private var lista: ArrayList<Pelicula>, val contexto: Context) : RecyclerView.Adapter<PeliculaAdapter.ViewHolder>() {


    inner class ViewHolder( var vista: View) : RecyclerView.ViewHolder(vista) {

        var imagen:ImageView
        val calificacion:TextView


        init {
            imagen = vista.findViewById(R.id.Iv_pelicula_portada)
            calificacion = vista.findViewById(R.id.Tv_calificacion_pelicula)



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
        holder.imagen.setImageResource(pelicula.ImagePortada)
        holder.calificacion.text = pelicula.Calificacion

        //////Imagen
        holder.imagen.setOnClickListener{
            contexto.startActivity(Intent(contexto, DetallePelicula::class.java).putExtra("pel",pelicula) )

        }
        //////Titulo
        /* holder.titulo.setOnClickListener{
             contexto.startActivity(Intent(contexto, VisorCalificacion::class.java).putExtra("pel",pelicula) )
         }
         //ImagenCertificado
         holder.imagen_certificado.setOnClickListener{
             contexto.startActivity(Intent(contexto, VisorCalificacion::class.java).putExtra("pel",pelicula) )
         }
         //Certificado
         holder.certificado.setOnClickListener{
             contexto.startActivity(Intent(contexto, VisorCalificacion::class.java).putExtra("pel",pelicula) )
         }*/


    }



}