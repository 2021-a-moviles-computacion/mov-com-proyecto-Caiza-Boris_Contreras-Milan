package com.example.replicacionimdb.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.replicacionimdb.R
import com.example.replicacionimdb.databinding.FragmentDashboardBinding
import com.example.replicacionimdb.ui.adapters.PeliculaAdapter
import com.example.replicacionimdb.ui.adapters.PeliculaSearchItemAdapter
import com.example.replicacionimdb.ui.clases.Pelicula
import com.example.replicacionimdb.ui.clases.PeliculaSearch

class DashboardFragment : Fragment() {

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



        //Peliculas Search
        val peliculasAdapter = PeliculaSearchItemAdapter(generarPeliculasSearch())

        var PeliculasSearchRV = root.findViewById<RecyclerView>(R.id.rv_searchPeliculas)
        PeliculasSearchRV.adapter = peliculasAdapter
        PeliculasSearchRV.layoutManager = GridLayoutManager(context, 2)

            //LinearLayoutManager(context,
            //LinearLayoutManager.VERTICAL, false)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun generarPeliculasSearch():ArrayList<PeliculaSearch>{
        var lista = ArrayList<PeliculaSearch>()


        lista.add(
            PeliculaSearch(
                R.drawable.eh_the_best_25_august,
                "Avances de películas más populares"
            )
        )
        lista.add(
            PeliculaSearch(
                R.drawable.eh_the_best_25_august,
                "Avances de películas recientes"
            )
        )
        lista.add(
            PeliculaSearch(
                R.drawable.eh_the_best_25_august,
                "Horarios de películas"
            )
        )
        lista.add(
            PeliculaSearch(
                R.drawable.eh_the_best_25_august,
                "Taquilla (US)"
            )
        )
        lista.add(
            PeliculaSearch(
                R.drawable.eh_the_best_25_august,
                "Películas mejor calificadas"
            )
        )
        lista.add(
            PeliculaSearch(
                R.drawable.eh_the_best_25_august,
                "Películas mas populares"
            )
        )




        return  lista
    }
}