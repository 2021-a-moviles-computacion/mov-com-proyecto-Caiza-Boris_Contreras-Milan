package com.example.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class IniciarSesion : AppCompatActivity() {
    val CODIGO_INICIO_SESION = 102
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        setup()
    }

    fun setup(){
        val boton = findViewById<Button>(R.id.aiccrearCuenta)
        val usuario = findViewById<EditText>(R.id.aicetusuario)
        val contasena = findViewById<EditText>(R.id.aicetcontraseña)
        boton.setOnClickListener {
            if (usuario.text.isNotBlank() && contasena.text.isNotBlank()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    usuario.text.toString(),
                    contasena.text.toString()
                ).addOnCompleteListener {
                    if(it.isSuccessful){
                        showHome(it.result?.user?.email?: "", ProviderType.BASIC)
                    }else{
                            showAlter()
                    }
                }
            }
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CODIGO_INICIO_SESION -> {
                if (resultCode == Activity.RESULT_OK) {
                    val usuario: IdpResponse? = IdpResponse.fromResultIntent(data)
                    if (usuario != null) {
                        if (usuario.isNewUser == true) {
                            Log.i("firebase-login", "Nuevo Usuario")
                        } else {
                            Log.i("firebase-login", "Usuario Antiguo")
                        }
                    }
                } else {
                    Log.i("firebase-login", "El usuario cancelo")
                }
            }
        }
    }




    fun showAlter(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")
        builder.setMessage("Se ha producido un error autenticado al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun showHome(email: String, provider: ProviderType){
        val homeIntent = Intent(this, Navigation::class.java).apply {
            putExtra("email", email )
            putExtra("provider", provider.name)

        }

        startActivity(homeIntent)
    }

}