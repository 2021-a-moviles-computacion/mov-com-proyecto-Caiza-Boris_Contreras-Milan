package com.example.replicacionimdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val botonIniciarSesion = findViewById<Button>(R.id.btn_iniciar_sesion)
        botonIniciarSesion.setOnClickListener {
            abrirActividad(IniciarSesion::class.java)
        }

        val botonRegistrarse = findViewById<Button>(R.id.btn_registrarse)
        botonRegistrarse.setOnClickListener {
            abrirActividad(RegistrarsePor::class.java)
        }
    }

    fun abrirActividad(clase: Class<*>){
        val intentExplicito = Intent(
            this,
            clase
        )
        startActivity(intentExplicito)
    }
}