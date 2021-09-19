package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class RegistrarsePor : AppCompatActivity() {

    private val GOOGLE = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse_por)

        val botonCrear = findViewById<Button>(R.id.aiccrearCuenta)
        botonCrear.setOnClickListener {
            abrirActividad(Registrarse::class.java)
        }

        val botonGoogle = findViewById<Button>(R.id.googleBoton)
        botonGoogle.setOnClickListener {
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()

            startActivityForResult(googleClient.signInIntent, GOOGLE)

        }
    }

    fun abrirActividad(clase: Class<*>){
        val intentExplicito = Intent( //Intent es una clase, solamente para que este bien contextualizado.
            this,
            clase
        )
        startActivity(intentExplicito) //Lo heredamos de la clase.
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == GOOGLE){
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)
                if(account!=null){
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener {
                            if(it.isSuccessful){
                                showHome(account.email ?: "", ProviderType.GOOGLE)
                            }else{
                                showAlter()
                            }
                        }
                }
            }catch (e: ApiException){
                showAlter()
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