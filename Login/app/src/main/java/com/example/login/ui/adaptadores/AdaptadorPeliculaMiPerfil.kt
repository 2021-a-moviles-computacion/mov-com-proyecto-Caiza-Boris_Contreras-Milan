package com.example.login.ui.adaptadores

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.login.DetallePelicula
import com.example.login.IniciarSesion
import com.example.login.Navigation
import com.example.login.R
import com.example.login.ui.clases.PeliculaFireBase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class AdaptadorPeliculaMiPerfil(private var lista: ArrayList<PeliculaFireBase>, val contexto: Context) : RecyclerView.Adapter<AdaptadorPeliculaMiPerfil.ViewHolder>() {

    // var listaOriginal: ArrayList<Pelicula>?=null
    var flagBotton: Boolean = false

    inner class ViewHolder(var vista: View) : RecyclerView.ViewHolder(vista) {

        var imagen: ImageView
        val calificacion: TextView
        val titulo: TextView
        var anio: TextView
        var clasificacion: TextView
        var duracion: TextView
        var boton: Button


        init {
            imagen = vista.findViewById(R.id.bpimagen)
            calificacion = vista.findViewById(R.id.bptvcalificacion)
            titulo = vista.findViewById(R.id.bptvtitulo)
            anio = vista.findViewById(R.id.tv_anio)
            clasificacion = vista.findViewById(R.id.tv_clasifcacion)
            duracion = vista.findViewById(R.id.tv_duracion)
            boton = vista.findViewById(R.id.tbpBtanadir)
            //listaOriginal!!.addAll(lista)


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mi_perfil_lista, parent, false)
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
        holder.titulo.text = pelicula.nombre
        holder.anio.text = pelicula.ano
        holder.clasificacion.text = pelicula.clasificacion
        holder.duracion.text = pelicula.duracion

        //////Imagen
        holder.imagen.setOnClickListener {
            contexto.startActivity(
                Intent(contexto, DetallePelicula::class.java).putExtra(
                    "pel",
                    pelicula
                )
            )

        }

        holder.boton.setOnClickListener {
            val instanciaAuth = FirebaseAuth.getInstance()
            val usuarioLocal = instanciaAuth.currentUser
            val db = Firebase.firestore
            Log.i("ayuda","${pelicula.uid}")
            val referencia = db
                .collection("usuario").document(usuarioLocal!!.email.toString())



            val peliculaAGuardar = hashMapOf<String,Any>(
                "peliculas" to FieldValue.delete()
            )
            referencia.update(peliculaAGuardar).addOnCompleteListener { }
            contexto.startActivity(Intent(contexto, Navigation::class.java))





        }
    }



}
