package com.example.replicacionimdb.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.replicacionimdb.R
import com.example.replicacionimdb.ui.clases.ExibidoHoy


class ExibidoHoyAdapter (
    val mList: List<ExibidoHoy>
): RecyclerView.Adapter<ExibidoHoyAdapter.ViewPagerViewHolder>(){
    inner class ViewPagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exhibicion_hoy,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val curmList = mList[position]

        val portadaImg =  holder.itemView.findViewById<ImageView>(R.id.Iv_imagen__exhi)
        portadaImg.setImageResource(curmList.Image)

        val infoPelicula =  holder.itemView.findViewById<TextView>(R.id.Tv_descripcion__exhi)
        infoPelicula.text = curmList.Descripcion

        when(curmList.Tipo){

            "List"->{
                val LogoImg =  holder.itemView.findViewById<ImageView>(R.id.Iv_icono_exhi)
                LogoImg.setImageResource(R.drawable.ic_baseline_format_list_bulleted_24)

                val tipo =  holder.itemView.findViewById<TextView>(R.id.Tv_tipo_exhi)
                tipo.text = "List"
            }

            "Photos"->{
                val LogoImg =  holder.itemView.findViewById<ImageView>(R.id.Iv_icono_exhi)
                LogoImg.setImageResource(R.drawable.ic_baseline_photo_library_24)

                val tipo =  holder.itemView.findViewById<TextView>(R.id.Tv_tipo_exhi)
                tipo.text = "Photos"
            }


            else ->{
                val LogoImg =  holder.itemView.findViewById<ImageView>(R.id.Iv_icono_exhi)
                LogoImg.setImageResource(R.drawable.ic_baseline_format_list_bulleted_24)

                val tipo =  holder.itemView.findViewById<TextView>(R.id.Tv_tipo_exhi)
                tipo.text = "List"
            }

        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

}