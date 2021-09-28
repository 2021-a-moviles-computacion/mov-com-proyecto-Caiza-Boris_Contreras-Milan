package com.example.login

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.login.databinding.ActivityNavigationBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

enum class ProviderType{
    BASIC,
    GOOGLE
}
class Navigation : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        val bundle = intent.extras
        val correo  = bundle?.getString("email")
        val provider2  = bundle?.getString("provider")
        val db  = FirebaseFirestore.getInstance()

        val peliculaAGuardar = hashMapOf<String,Any?>()

        db.collection("usuario").document(correo.toString())
            .set(
                hashMapOf("correo" to correo.toString(),
                           "provider" to provider2.toString(),
                            "peliculas" to peliculaAGuardar)
            )


      /*  val  prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
            .edit()

        prefs.putString("email",correo)
        prefs.putString("provider",provider2)
        prefs.apply()*/




        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_navigation)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

}

