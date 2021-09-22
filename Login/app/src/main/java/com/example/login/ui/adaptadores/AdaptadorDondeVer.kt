package com.example.login.ui.adaptadores

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login.DetallePelicula
import com.example.login.R
import com.example.login.ui.clases.DondeVer
import com.example.login.ui.clases.Pelicula

class AdaptadorDondeVer(private var lista: ArrayList<DondeVer>, val contexto: Context) : RecyclerView.Adapter<AdaptadorDondeVer.ViewHolder>() {


    inner class ViewHolder( var vista: View) : RecyclerView.ViewHolder(vista) {

        var logo: ImageView
        val nombre: TextView



        init {
            logo = vista.findViewById(R.id.dvIvImagen)
            nombre = vista.findViewById(R.id.dvTvNombre)




        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemdondever,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dondever = lista[position]
        holder.logo.setImageResource(dondever.portado)
        holder.nombre.text = dondever.nombre


        //////Imagen
      /*  holder.imagen.setOnClickListener{
            contexto.startActivity(Intent(contexto, DetallePelicula::class.java).putExtra("pel",pelicula) )

        }*/
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