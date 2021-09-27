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
import java.util.function.Predicate

class AdaptadorPeliculaBusqueda(private var lista: ArrayList<PeliculaFireBase>, val contexto: Context) : RecyclerView.Adapter<AdaptadorPeliculaBusqueda.ViewHolder>() {

   // var listaOriginal: ArrayList<Pelicula>?=null

    inner class ViewHolder( var vista: View) : RecyclerView.ViewHolder(vista) {

        var imagen: ImageView
        val calificacion: TextView
        val titulo: TextView
        var anio:TextView
        var clasificacion: TextView
        var duracion: TextView


        init {
            imagen = vista.findViewById(R.id.bpimagen)
            calificacion = vista.findViewById(R.id.bptvcalificacion)
            titulo = vista.findViewById(R.id.bptvtitulo)
            anio = vista.findViewById(R.id.tv_anio)
            clasificacion =  vista.findViewById(R.id.tv_clasifcacion)
            duracion = vista.findViewById(R.id.tv_duracion)
            //listaOriginal!!.addAll(lista)



        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itembusquedapelicula,parent,false)
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




        holder.calificacion.text = pelicula.calificacion.toString()
        holder.titulo.text = pelicula.uid
        holder.anio.text = pelicula.ano
        holder.clasificacion.text = pelicula.clasificacion
        holder.duracion.text = pelicula.duracion

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