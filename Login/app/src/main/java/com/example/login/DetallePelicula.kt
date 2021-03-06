package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.login.ui.adaptadores.AdaptadorCategoria
import com.example.login.ui.adaptadores.AdaptadorDondeVer
import com.example.login.ui.adaptadores.AdaptadorPremios
import com.example.login.ui.adaptadores.PersonaAdapter
import com.example.login.ui.clases.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class DetallePelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pelicula)





        val pelicula = intent.getSerializableExtra("pel") as PeliculaFireBase

        val storage = Firebase.storage
        var imagenRefPortada = storage.getReferenceFromUrl(pelicula.portadaLink.toString())
        var imagen = findViewById<ImageView>(R.id.daIvImagen)
        Glide.with(this)
            .load(imagenRefPortada)
            .into(imagen)
        val db = Firebase.firestore
        val peliculasInicio = db.collection("DetallePelicula").document(pelicula.uid.toString())
        Log.e("help1","uid: ${pelicula.uid.toString()}")
        peliculasInicio
            .get()
            .addOnSuccessListener {
                pelicula.IdYouTube=it["IdYouTube"].toString()
                pelicula.categorias=it["categorias"].toString()
                pelicula.dondeVer=null
                pelicula.portadaTrailer=it["portadaTrailer"].toString()
                pelicula.reparto=null
                pelicula.sinopsis=it["sinopsis"].toString()


                //Categoria
                val arrayPremios= ArrayList<Categoria>()
                var hashCategoria = it["categorias"] as HashMap<String,Any>
                hashCategoria.forEach{
                    arrayPremios.add(Categoria(it.value.toString()))
                }

                var reparto2 = findViewById<RecyclerView>(R.id.dpRvCategoria)
                reparto2.adapter = AdaptadorCategoria(arrayPremios, this)
                reparto2.layoutManager = LinearLayoutManager(this,
                    LinearLayoutManager.HORIZONTAL, false)


                //Reparto
                val hashReparto = it["reparto"] as HashMap<String,HashMap<String,String>>
                val arrayReparto= ArrayList<ActorFireBase>()
                for ((key,value) in hashReparto){
                    arrayReparto.add(
                        ActorFireBase(
                        value["uidActor"],
                        null,
                            value["nombre"],
                            null,
                            null,
                            value["imagen"],
                            value["personaje"]
                    )
                    )
                }

                var reparto = findViewById<RecyclerView>(R.id.recyclerView2)
                reparto.adapter = PersonaAdapter(arrayReparto, this)
                reparto.layoutManager = LinearLayoutManager(this,
                    LinearLayoutManager.HORIZONTAL, false)





                var imagenRefTrailer = storage.getReferenceFromUrl(pelicula.portadaTrailer.toString())

                var portada = findViewById<ImageView>(R.id.dpivportada)
                Glide.with(this)
                    .load(imagenRefTrailer)
                    .into(portada)
                var titulo = findViewById<TextView>(R.id.dpTvTitulo)
                titulo.setText(pelicula.nombre)
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


                var portadaBoton = findViewById<ImageView>(R.id.dpivportada)
                portadaBoton.setOnClickListener {
                    startActivity(Intent(this, videoYoutube2::class.java).putExtra("youTubeID",pelicula.IdYouTube) )
                }

            }
            .addOnFailureListener{

            }

        val botonAnadir =findViewById<Button>(R.id.button2)
        botonAnadir.setOnClickListener {

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
                                val hashPeliculas =
                                    documentoActual.get("peliculas") as HashMap<String, Any>
                                Log.i("transaccion", "hash: ${hashPeliculas}")

                                if (hashPeliculas != {}) {
                                    hashPeliculas.put(pelicula.uid.toString(), atributos)
                                    transaction.update(referencia, "peliculas", hashPeliculas)
                                } else {
                                    transaction.update(referencia, "peliculas", peliculaAGuardar)
                                }

                            }
                            .addOnSuccessListener {
                                Log.i("transaccion", "Transaccion Completa")
                            }
                            .addOnFailureListener {
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

                    }                    }
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