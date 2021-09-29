package com.example.replicacionimdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.replicacionimdb.ui.adapters.PersonaAdapter
import com.example.replicacionimdb.ui.clases.Pelicula

class DetallePelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pelicula)


        val pelicula = intent.getSerializableExtra("pel") as Pelicula

        var prueba = findViewById<TextView>(R.id.textView8)
        prueba.setText("${pelicula.Title}")
    }
}