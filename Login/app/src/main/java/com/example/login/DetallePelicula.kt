package com.example.login

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.ui.adaptadores.PersonaAdapter
import com.example.login.ui.clases.Pelicula

class DetallePelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pelicula)

        val pelicula = intent.getSerializableExtra("pel") as Pelicula

        var portada = findViewById<ImageView>(R.id.dpivportada)
        portada.setImageResource(pelicula.ImageTrailer)

        var imagen = findViewById<ImageView>(R.id.dpivimagen)
        imagen.setImageResource(pelicula.ImagePortada)

        var reparto = findViewById<RecyclerView>(R.id.recyclerView2)
        reparto.adapter = PersonaAdapter(pelicula.Actores)
        reparto.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false)
    }
}