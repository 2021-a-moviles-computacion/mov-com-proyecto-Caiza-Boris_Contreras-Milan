package com.example.login.ui.adaptadores

import android.R.attr
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.login.DetallePelicula
import com.example.login.R
import com.example.login.ui.clases.Pelicula
import com.example.login.ui.clases.PeliculaFireBase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.function.Predicate
import android.R.attr.button
import android.annotation.SuppressLint
import com.google.firebase.firestore.FirebaseFirestore


class AdaptadorPeliculaBusqueda(private var lista: ArrayList<PeliculaFireBase>, val contexto: Context) : RecyclerView.Adapter<AdaptadorPeliculaBusqueda.ViewHolder>() {

   companion object{
       var flagBotton:Boolean = false
   }

    inner class ViewHolder( var vista: View) : RecyclerView.ViewHolder(vista) {

        var imagen: ImageView
        val calificacion: TextView
        val titulo: TextView
        var anio:TextView
        var clasificacion: TextView
        var duracion: TextView
        var boton: Button


        init {
            imagen = vista.findViewById(R.id.bpimagen)
            calificacion = vista.findViewById(R.id.bptvcalificacion)
            titulo = vista.findViewById(R.id.bptvtitulo)
            anio = vista.findViewById(R.id.tv_anio)
            clasificacion =  vista.findViewById(R.id.tv_clasifcacion)
            duracion = vista.findViewById(R.id.tv_duracion)
            boton = vista.findViewById(R.id.tbpBtanadir)
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
        holder.titulo.text = pelicula.nombre
        holder.anio.text = pelicula.ano
        holder.clasificacion.text = pelicula.clasificacion
        holder.duracion.text = pelicula.duracion

        //////Imagen
        holder.imagen.setOnClickListener{
            contexto.startActivity(Intent(contexto, DetallePelicula::class.java).putExtra("pel",pelicula) )

        }


        holder.boton.setOnClickListener {
         flagBotton = false
            botonCambiar(holder)


            val atributos = hashMapOf<String,String>(
                "ano" to pelicula.ano.toString(),
                "calificacion" to pelicula.calificacion.toString(),
                "clasificacion" to pelicula.clasificacion.toString(),
                "duracion" to pelicula.duracion.toString(),
                "imagen" to pelicula.portadaLink.toString(),
                "nombre" to pelicula.nombre.toString(),
                "uid_DetallePelicula" to pelicula.uid.toString()
            )


            val peliculaAGuardar = hashMapOf<String,HashMap<String,String>>(
                pelicula.uid.toString() to atributos
            )


            val instanciaAuth = FirebaseAuth.getInstance()
            val usuarioLocal = instanciaAuth.currentUser

            val db = Firebase.firestore

            Log.i("transaccion", "User : ${usuarioLocal!!.email}")
            val referencia = db
                .collection("usuario").document(usuarioLocal!!.email.toString())

            referencia
                .get()
                .addOnSuccessListener {
                    if(it["peliculas"] !=null) {

            db
                .runTransaction { transaction ->
                    val documentoActual = transaction.get(referencia)
                    val hashPeliculas = documentoActual.get("peliculas") as HashMap<String,Any>
                    Log.i("transaccion", "hash: ${hashPeliculas}")

                    if(hashPeliculas != {}){
                        hashPeliculas.put(pelicula.uid.toString(), atributos)
                        transaction.update(referencia, "peliculas", hashPeliculas)
                    }else{
                        transaction.update(referencia, "peliculas", peliculaAGuardar)
                    }

                }
                .addOnSuccessListener {
                    Log.i("transaccion", "Transaccion Completa")
                }
                .addOnFailureListener{
                    Log.i("transaccion", "ERROR")
                }


        }else{
                        val peliculaAGuardar = hashMapOf<String,Any?>()

                        db.collection("usuario").document(usuarioLocal.email.toString())
                            .set(

                                hashMapOf( "correo" to usuarioLocal.email.toString()
                                    ,"peliculas" to peliculaAGuardar)
                            )
                        db
                            .runTransaction { transaction ->
                                val documentoActual = transaction.get(referencia)
                                val hashPeliculas = documentoActual.get("peliculas") as HashMap<String,Any>
                                Log.i("transaccion", "hash: ${hashPeliculas}")

                                if(hashPeliculas != {}){
                                    hashPeliculas.put(pelicula.uid.toString(), atributos)
                                    transaction.update(referencia, "peliculas", hashPeliculas)
                                }else{
                                    transaction.update(referencia, "peliculas", peliculaAGuardar)
                                }

                            }
                            .addOnSuccessListener {
                                Log.i("transaccion", "Transaccion Completa")
                            }
                            .addOnFailureListener{
                                Log.i("transaccion", "ERROR")
                            }




                    }        }
                    }

    }

@SuppressLint("ResourceAsColor")
fun botonCambiar(holder: ViewHolder){
    if(flagBotton){

        holder.boton.setBackgroundColor(R.color.black)
        holder.boton.setText("AÑADIR")
        flagBotton=false
    }else{

        holder.boton.setBackgroundColor(R.color.verde)
        holder.boton.setText("GUARDADO")
        flagBotton=true
    }
}

}