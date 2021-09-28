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
import com.example.login.DetalleActor
import com.example.login.DetallePelicula
import com.example.login.R
import com.example.login.ui.clases.ActorFireBase
import com.example.login.ui.clases.Persona
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class PersonaAdapter (
    val mList: List<ActorFireBase>,
    val contexto: Context
    ): RecyclerView.Adapter<PersonaAdapter.ViewPagerViewHolder>(){
    inner class ViewPagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var imagen: ImageView
        val nombreActor: TextView
        val nombrePersonaje: TextView



        init {
            imagen = itemView.findViewById(R.id.Iv_personaImagen)
            nombreActor =  itemView.findViewById(R.id.Tv_nombre_persona)
            nombrePersonaje =  itemView.findViewById(R.id.tv_nombre_persona_pelicula)



        }
    }


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


        val storage = Firebase.storage
        var imagenRef = storage.getReferenceFromUrl(curmList.imagen.toString())


        Glide.with(contexto)
            .load(imagenRef)
            .into(holder.imagen)

        holder.nombreActor.text = curmList.nombre.toString()
        holder.nombrePersonaje.text = curmList.personaje.toString()


        holder.imagen.setOnClickListener{
            contexto.startActivity(Intent(contexto, DetalleActor::class.java).putExtra("actor",curmList) )

        }


    }
}