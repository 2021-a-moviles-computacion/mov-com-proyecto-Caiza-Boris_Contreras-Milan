package com.example.login

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.login.ui.adaptadores.AdaptadorDondeVer
import com.example.login.ui.adaptadores.PersonaAdapter
import com.example.login.ui.clases.DondeVer
import com.example.login.ui.clases.Pelicula
import com.example.login.ui.clases.PeliculaFireBase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import org.w3c.dom.Text

class DetallePelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pelicula)

        val pelicula = intent.getSerializableExtra("pel") as PeliculaFireBase

        val storage = Firebase.storage
        var imagenRefPortada = storage.getReferenceFromUrl(pelicula.portadaLink.toString())
        var imagen = findViewById<ImageView>(R.id.dpivimagen)
        Glide.with(this)
            .load(imagenRefPortada)
            .into(imagen)
        val db = Firebase.firestore
        val peliculasInicio = db.collection("DetallePelicula").document(pelicula.uid.toString())
        Log.e("help1","uid: ${pelicula.uid.toString()}")
        peliculasInicio
            .get()
            .addOnSuccessListener {
                //Log.e("help1","link1: ${it.data}")
                pelicula.IdYouTube=it["IdYouTube"].toString()
                pelicula.categorias=it["categorias"].toString()
                pelicula.dondeVer=null
                pelicula.portadaTrailer=it["portadaTrailer"].toString()
                pelicula.reparto=null
                pelicula.sinopsis=it["sinopsis"].toString()

                Log.e("help1","link: ${pelicula}")

                var imagenRefTrailer = storage.getReferenceFromUrl(pelicula.portadaTrailer.toString())

                var portada = findViewById<ImageView>(R.id.dpivportada)
                Glide.with(this)
                    .load(imagenRefTrailer)
                    .into(portada)
                var titulo = findViewById<TextView>(R.id.dpTvTitulo)
                titulo.setText(pelicula.uid)
                var anio = findViewById<TextView>(R.id.dpTvAnio)
                anio.setText(pelicula.ano)
                var clasificacion = findViewById<TextView>(R.id.dpTvClasificacion)
                clasificacion.setText(pelicula.clasificacion)
                var duracion = findViewById<TextView>(R.id.dpTvDuracion)
                duracion.setText(pelicula.duracion)
                var sinopsis = findViewById<TextView>(R.id.dpTvSinopsis)
                sinopsis.setText(pelicula.sinopsis)
                sinopsis.setOnClickListener {
                    this.startActivity(Intent(this,DetalleSinopsis::class.java).putExtra("pelicula",pelicula))
                }
                var calificacion = findViewById<TextView>(R.id.dpTvCalificacion)
                calificacion.setText(pelicula.calificacion.toString())

                var categorias = pelicula.categorias
                Log.i("help","${categorias}")



            }
            .addOnFailureListener{

            }

        var dondeVer = findViewById<RecyclerView>(R.id.dpRvDondeVer)
        dondeVer.adapter = AdaptadorDondeVer(dondeVer(),this)
        dondeVer.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false)
    }

    fun dondeVer():ArrayList<DondeVer>{
        var lista = ArrayList<DondeVer>()
        lista.add(DondeVer(R.drawable.netflixlogo,"Netflix"))
        lista.add(DondeVer(R.drawable.logodisney,"Disney +"))
        return  lista
    }
}