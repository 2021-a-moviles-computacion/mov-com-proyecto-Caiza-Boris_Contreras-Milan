package com.example.login.ui.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.ui.clases.Premio

class AdaptadorPremios(private var lista: ArrayList<Premio>, val contexto: Context) : RecyclerView.Adapter<AdaptadorPremios.ViewHolder>() {


    inner class ViewHolder( var vista: View) : RecyclerView.ViewHolder(vista) {


        val nombre: TextView



        init {

            nombre = vista.findViewById(R.id.icTvCategoria)




        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_premios,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nombre = lista[position]


        holder.nombre.text = nombre.nombre



    }



}