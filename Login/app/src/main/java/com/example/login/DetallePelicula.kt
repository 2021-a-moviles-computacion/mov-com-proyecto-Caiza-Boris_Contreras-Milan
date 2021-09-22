package com.example.login

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.ui.adaptadores.AdaptadorDondeVer
import com.example.login.ui.adaptadores.PersonaAdapter
import com.example.login.ui.clases.DondeVer
import com.example.login.ui.clases.Pelicula
import org.w3c.dom.Text

class DetallePelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pelicula)

        val pelicula = intent.getSerializableExtra("pel") as Pelicula

        var portada = findViewById<ImageView>(R.id.dpivportada)
        portada.setImageResource(pelicula.ImageTrailer)

        var imagen = findViewById<ImageView>(R.id.dpivimagen)
        imagen.setImageResource(pelicula.ImagePortada)

        var titulo = findViewById<TextView>(R.id.dpTvTitulo)
        titulo.setText(pelicula.Title)

        var anio = findViewById<TextView>(R.id.dpTvAnio)
        anio.setText(pelicula.Ano)

        var clasificacion = findViewById<TextView>(R.id.dpTvClasificacion)
        clasificacion.setText(pelicula.Clasificacion)

        var duracion = findViewById<TextView>(R.id.dpTvDuracion)
        duracion.setText(pelicula.Duracion)

        var sinopsis = findViewById<TextView>(R.id.dpTvSinopsis)
        sinopsis.setText(pelicula.Sinopsis)

        var calificacion = findViewById<TextView>(R.id.dpTvCalificacion)
        calificacion.setText(pelicula.Calificacion)

        var reparto = findViewById<RecyclerView>(R.id.recyclerView2)
        reparto.adapter = PersonaAdapter(pelicula.Actores)
        reparto.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false)

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