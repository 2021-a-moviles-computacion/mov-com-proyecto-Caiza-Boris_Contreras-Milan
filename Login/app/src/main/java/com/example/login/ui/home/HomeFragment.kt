package com.example.login.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.ui.clases.Pelicula
import com.example.login.ui.clases.Persona
import com.example.login.databinding.FragmentHomeBinding
import com.example.login.ui.adaptadores.AdaptadorPelicula
import com.example.login.ui.adaptadores.ViewPagerAdapter2
import com.example.login.ui.clases.PeliculaFireBase
import com.example.login.ui.clases.Slide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val context = container!!.context



        ////Slider

        var recyclerSlider = root.findViewById<RecyclerView>(R.id.rvSlider2)


        recyclerSlider.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)
        // recycler.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        recyclerSlider.adapter = ViewPagerAdapter2(generarSlider(),context)

        //Peliculas

        ////Peliculas
        var recyclerPelicula = root.findViewById<RecyclerView>(R.id.home_rv_pelicula)
        var recyclerPelicula2 = root.findViewById<RecyclerView>(R.id.home_rv_pelicula2)
        var recyclerPelicula3 = root.findViewById<RecyclerView>(R.id.home_rv_pelicula3)
        recyclerPelicula.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)
        recyclerPelicula2.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)
        recyclerPelicula3.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)
        // recycler.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))

        var listaPeliculas = ArrayList<PeliculaFireBase>()

        val db = Firebase.firestore
        val peliculasInicio = db.collection("Pelicula")

        peliculasInicio
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

                }

                recyclerPelicula.adapter = AdaptadorPelicula(listaPeliculas,context)
                recyclerPelicula2.adapter = AdaptadorPelicula(listaPeliculas,context)
                recyclerPelicula3.adapter = AdaptadorPelicula(listaPeliculas,context)
            }
            .addOnFailureListener{

            }

        return root
    }



    private fun generarSlider():ArrayList<Slide>{
        var lista = ArrayList<Slide>()

        lista.add(
            Slide(
                R.drawable.pl_t_pulp_fiction,

            )
        )

        lista.add(
            Slide(
                R.drawable.pl_t_a_clockwork_orange,

            )
        )
        return  lista
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}