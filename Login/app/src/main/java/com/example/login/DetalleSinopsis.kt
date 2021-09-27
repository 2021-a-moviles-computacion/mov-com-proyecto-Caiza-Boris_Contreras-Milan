package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.login.ui.clases.PeliculaFireBase

class DetalleSinopsis : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_sinopsis)

        val pelicula = intent.getSerializableExtra("pelicula") as PeliculaFireBase

        var sinopsis = findViewById<TextView>(R.id.dsTvSinopsis)
        sinopsis.setText(pelicula.sinopsis)
    }
}