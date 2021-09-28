package com.example.login.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.databinding.FragmentDashboardBinding
import com.example.login.ui.adaptadores.AdaptadorPeliculaBusqueda
import com.example.login.ui.clases.Pelicula
import com.example.login.ui.clases.PeliculaFireBase
import com.example.login.ui.clases.Persona
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    private var sear: SearchView ?=null
    private  var PeliculasMasBuscadas= ArrayList<PeliculaFireBase>()


    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        ////Peliculas
        val context = container!!.context
        var recyclerPelicula = root.findViewById<RecyclerView>(R.id.fdrvbusquedapeliculas)


        recyclerPelicula.layoutManager = LinearLayoutManager(context)
        // recycler.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))

        var listaPeliculas = ArrayList<PeliculaFireBase>()

        val db = Firebase.firestore
        val peliculasInicio = db.collection("Pelicula")

        peliculasInicio
            .get()
            .addOnSuccessListener {
                for(pelicula in it){
                    PeliculasMasBuscadas.add(
                        PeliculaFireBase(
                            pelicula["uid_DetallePelicula"].toString(),
                            null,
                            pelicula["ano"].toString(),
                            pelicula["calificacion"].toString().toDouble(),
                            null,
                            pelicula["clasificacion"].toString(),
                            null,
                            pelicula["duracion"].toString(),
                            pelicula["nombre"].toString(),
                            pelicula["imagen"].toString(),
                            null,
                            null,
                            null,
                        )
                    )

                }

                recyclerPelicula.adapter = AdaptadorPeliculaBusqueda(PeliculasMasBuscadas,context)
            }
            .addOnFailureListener{

            }


        var buscador = root.findViewById<SearchView>(R.id.sv_buscador)
        buscador.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                //PeliculaBuscada.clear()
                Log.i("search","query: ${query}")
                var listaPeliculas = ArrayList<PeliculaFireBase>()

                val db = Firebase.firestore
                val peliculasInicio = db.collection("Pelicula")

                peliculasInicio
                    .whereEqualTo("nombre", query)
                    //.whereArrayContains("nombre",query!!)
                    .get()
                    .addOnSuccessListener {
                        for(pelicula in it){
                            listaPeliculas.add(
                                PeliculaFireBase(
                                    pelicula["uid_DetallePelicula"].toString(),
                                    null,
                                    pelicula["ano"].toString(),
                                    pelicula["calificacion"].toString().toDouble(),
                                    null,
                                    pelicula["clasificacion"].toString(),
                                    null,
                                    pelicula["duracion"].toString(),
                                    pelicula["nombre"].toString(),
                                    pelicula["imagen"].toString(),
                                    null,
                                    null,
                                    null,
                                )
                            )
                            Log.i("search","pelicula uid: ${pelicula["uid_DetallePelicula"].toString()}")
                        }
                        recyclerPelicula.adapter = AdaptadorPeliculaBusqueda(listaPeliculas,context)
                    }
                    .addOnFailureListener{
                        Log.i("search","Error: ${it}")
                    }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText == ""){
                    recyclerPelicula.adapter = AdaptadorPeliculaBusqueda(PeliculasMasBuscadas,context)
                }
                return true
            }
        })





        return root
    }
}