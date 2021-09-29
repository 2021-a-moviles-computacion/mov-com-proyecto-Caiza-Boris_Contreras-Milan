package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.login.ui.adaptadores.AdaptadorPeliculaActor
import com.example.login.ui.adaptadores.AdaptadorPremios
import com.example.login.ui.adaptadores.PersonaAdapter
import com.example.login.ui.clases.ActorFireBase
import com.example.login.ui.clases.PeliculaFireBase
import com.example.login.ui.clases.Premio
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class DetalleActor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_actor)


        val storage = Firebase.storage
        var actor = intent.getSerializableExtra("actor") as ActorFireBase

        var titulo = findViewById<TextView>(R.id.daTvTitulo)
        titulo.setText(actor.nombre)
        Log.i("help","Uid: : +${actor.uid}+")

        val db = Firebase.firestore
        val actorInicio = db.collection("ActorDetalle").document(actor.uid.toString())
        actorInicio.get().addOnSuccessListener {
            actor.biografia = it["biografia"].toString()


            //Premios
            val arrayPremios= ArrayList<Premio>()
            var hashReparto = it["premios"] as HashMap<String,Any>
            hashReparto.forEach{
                arrayPremios.add(Premio(it.value.toString()))
            }

            var reparto = findViewById<RecyclerView>(R.id.daRvCategorias)
            reparto.adapter = AdaptadorPremios(arrayPremios, this)
            reparto.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false)


           // Log.i("nose","${arrayPremios}")
        //Pelicula
            val hashPelicula = it["peliculas"] as HashMap<String,HashMap<String,String>>
            val arrayPelicula= ArrayList<PeliculaFireBase>()
            for ((key,value) in hashPelicula){
                arrayPelicula.add(
                    PeliculaFireBase(
                       value["uidDetallePelicula"].toString(),
                        null,
                        value["ano"].toString(),
                        value["calificacion"].toString().toDouble(),
                        null,
                        value["clasificacion"].toString(),
                        null,
                        value["duracion"].toString(),
                        value["nombre"].toString(),
                        value["imagen"].toString(),
                        null,
                        null,
                        null

                    )
                )
            }

            var reparto2 = findViewById<RecyclerView>(R.id.daRvPelicula)
            reparto2.adapter = AdaptadorPeliculaActor(arrayPelicula, this)
            reparto2.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false)



            var biografia = findViewById<TextView>(R.id.daTvBiografía)
            biografia.setText(actor.biografia)



        }.addOnFailureListener{
            Log.i("help","Error")
        }


        var imagen = findViewById<ImageView>(R.id.daIvImagen)
        var imagenRefPortada = storage.getReferenceFromUrl(actor.imagen.toString())
        Glide.with(this)
            .load(imagenRefPortada)
            .into(imagen)









    }
}