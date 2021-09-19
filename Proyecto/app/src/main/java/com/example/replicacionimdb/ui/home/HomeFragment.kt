package com.example.replicacionimdb.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.replicacionimdb.R
import com.example.replicacionimdb.databinding.FragmentHomeBinding
import com.example.replicacionimdb.ui.adapters.ExibidoHoyAdapter
import com.example.replicacionimdb.ui.adapters.PeliculaAdapter
import com.example.replicacionimdb.ui.adapters.ViewPagerAdapter2
import com.example.replicacionimdb.ui.clases.ExibidoHoy
import com.example.replicacionimdb.ui.clases.Pelicula
import com.example.replicacionimdb.ui.clases.Persona
import com.example.replicacionimdb.ui.clases.Slide
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private var lstSlides= ArrayList<Slide>()

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



        //Slides
        lstSlides=generarSlider()
        val adapter = ViewPagerAdapter2(lstSlides)

        val pager = root.findViewById<ViewPager2>(R.id.slidier_pager)
        pager.adapter = adapter


        var timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                pager.post(Runnable {
                    Log.d("viewPager", "" + pager.currentItem+" size: ${lstSlides.size-1}")
                    if(pager.currentItem<lstSlides.size-1){
                        pager.currentItem = pager.currentItem+1
                    }else{
                        pager.currentItem=0
                    }
                })
            }
        }, 4000, 3000)

        //Exhibidas hoy
        val exhibidosHoyAdapter = ExibidoHoyAdapter(generarExhibidoHoy())

        var exhibidosHoyRV = root.findViewById<RecyclerView>(R.id.rv_ExhibidosHoy)
        exhibidosHoyRV.adapter = exhibidosHoyAdapter
        exhibidosHoyRV.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)


        //Peliculas
        val peliculasAdapter = PeliculaAdapter(generarPeliculas(),findNavController())

        var PeliculasRV = root.findViewById<RecyclerView>(R.id.rv_Peliculas)
        PeliculasRV.adapter = peliculasAdapter
        PeliculasRV.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)




        //val anadirALista = root.findViewById<ImageButton>(R.id.btn_anadirLista)

        //anadirALista.setOnClickListener {
       // findNavController().navigate(R.id.navigation_actividadDetallePelicula)
       //}


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun generarSlider():ArrayList<Slide>{
        var lista = ArrayList<Slide>()
        lista.add(
            Slide(
                R.drawable.pl_sl_house_of_gucci,
                R.drawable.pl_p_house_of_gucci,
                "House of Gucci",
                "Official Trailer"
            )
        )
        lista.add(
            Slide(
                R.drawable.pl_t_pulp_fiction,
                R.drawable.pl_p_pulp_fiction,
                "Pulp Fiction",
                "Official Trailer"
            )
        )

        lista.add(
            Slide(
                R.drawable.pl_t_a_clockwork_orange,
                R.drawable.pl_p_a_clockwork_orange,
                "La naranja Mecánica",
                "Official Trailer"
            )
        )
        return  lista
    }

    private fun generarExhibidoHoy():ArrayList<ExibidoHoy>{
        var lista = ArrayList<ExibidoHoy>()

        lista.add(
            ExibidoHoy(
                R.drawable.eh_the_best_25_august,
                "The 25 Best Things to Watch in August",
                "List"
            )
        )
        lista.add(
            ExibidoHoy(
                R.drawable.eh_stars_at_the_beach,
                "Vintage Looks: Stars at the Beach",
                "Photos"
            )
        )


        return  lista
    }

    private fun generarPeliculas():ArrayList<Pelicula>{
        var lista = ArrayList<Pelicula>()

        //pulp fiction
        var categoriasPelicula1 = ArrayList<String>()
        categoriasPelicula1.add("Crime")
        categoriasPelicula1.add("Drama")

        lista.add(
            Pelicula(
                R.drawable.pl_p_pulp_fiction,
                R.drawable.pl_t_pulp_fiction,
                "Pulp Fiction",
                "8.9",
                "1994",
                "1994 R 2h 34min",
                "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
                "Quentin Tarantino",
                "Quentin Tarantino(stories by) Roger Avary(stories by)",
                categoriasPelicula1,
                generarPersonasPulp()


            )
        )

        //interestellar
        var categoriasPelicula2 = ArrayList<String>()
        categoriasPelicula2.add("Adventure")
        categoriasPelicula2.add("Drama")
        categoriasPelicula2.add("Sci-fi")
        lista.add(
            Pelicula(
                R.drawable.pl_p_interstellar,
                R.drawable.pl_t_interstellar,
                "Interstellar",
                "8.6",
                "2014",
                "2014 PG-13 2h 49min",
                "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
                "Christopher Nolan",
                "Jonathan Nolan Christopher Nolan",
                categoriasPelicula2,
                generarPersonasInterestellar()

            )
        )


        //the notebook
        var categoriasPelicula3 = ArrayList<String>()
        categoriasPelicula3.add("Drama")
        categoriasPelicula3.add("Romance")

        lista.add(
            Pelicula(
                R.drawable.pl_p_the_notebook,
                R.drawable.pl_t_the_notebook,
                "The Notebook",
                "7.8",
                "2004",
                "2004 PG-13 2h 3min",
                "A poor yet passionate young man falls in love with a rich young woman, giving her a sense of freedom, but they are soon separated because of their social differences.",
                "Nick Cassavetes",
                "Jeremy Leven(screenplay) Jan Sardi(adaptation) Nicholas Sparks(novel)",
                categoriasPelicula3,
                generarPersonasNotebook()

            )
        )


        //Naranja mecanica
        var categoriasPelicula4 = ArrayList<String>()
        categoriasPelicula4.add("Crime")
        categoriasPelicula4.add("Drama")
        categoriasPelicula4.add("Sci-Fi")

        lista.add(
            Pelicula(
                R.drawable.pl_p_a_clockwork_orange,
                R.drawable.pl_t_a_clockwork_orange,
                "A Clockwork Orange",
                "8.3",
                "1971",
                "1971 R 2h 16min",
                "In the future, a sadistic gang leader is imprisoned and volunteers for a conduct-aversion experiment, but it doesn't go as planned. ",
                "Stanley Kubrick",
                "Stanley Kubrick(screenplay) Anthony Burgess(novel)",
                categoriasPelicula4,
                generarPersonasNaranja()

            )
        )

        //matrix
        var categoriasPelicula5 = ArrayList<String>()
        categoriasPelicula5.add("Action")
        categoriasPelicula5.add("Sci-Fi")

        lista.add(
            Pelicula(
                R.drawable.pl_p_the_matrix,
                R.drawable.pl_t_the_matrix,
                "The Matrix",
                "8.7",
                "1999",
                "1999 R 2h 16min",
                "When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.",
                "Lilly Wachowski Lana Wachowski",
                "Lilly Wachowski Lana Wachowski",
                categoriasPelicula5,
                generarPersonasMatrix()

            )
        )

        return  lista
    }


    private fun generarPersonas():ArrayList<Persona>{
        var lista = ArrayList<Persona>()

        lista.add(
            Persona(
                R.drawable.pr_scarlet_johanson,
                "Scarleth Johanson",
                "Natasha Romanoff"
            )
        )
        lista.add(
            Persona(
                R.drawable.pr_scarlet_johanson,
                "Scarleth Johanson2",
                "Natasha Romanoff"
            )
        )
        lista.add(
            Persona(
                R.drawable.pr_scarlet_johanson,
                "Scarleth Johanson3",
                "Natasha Romanoff"
            )
        )

        return lista
    }


    private fun generarPersonasInterestellar():ArrayList<Persona>{
        var lista = ArrayList<Persona>()

        lista.add(
            Persona(
                R.drawable.pr_matthew_mcconaughey,
                "Matthew McConaughey",
                "Cooper"
            )
        )
        lista.add(
            Persona(
                R.drawable.pr_mackenzie_foy,
                "Mackenzie Foy",
                "Murph"
            )
        )
        lista.add(
            Persona(
                R.drawable.pr_timothee_chalamet,
                "Timothée Chalamet",
                "Tom"
            )
        )

        lista.add(
            Persona(
                R.drawable.pr_anne_hathaway,
                "Anne Hathaway",
                "Brand"
            )
        )
        lista.add(
            Persona(
                R.drawable.pr_matt_damon,
                "Matt Damon",
                "Mann"
            )
        )

        return lista
    }

    private fun generarPersonasPulp():ArrayList<Persona>{
        var lista = ArrayList<Persona>()

        lista.add(
            Persona(
                R.drawable.pr_john_travolta,
                "John Travolta",
                "Vincent Vega"
            )
        )
        lista.add(
            Persona(
                R.drawable.pr_uma_thurman,
                "Uma Thurman",
                "Mia Wallace"
            )
        )
        lista.add(
            Persona(
                R.drawable.pr_samuel_jackson,
                "Samuel L. Jackson",
                "Jules Winnfield"
            )
        )

        lista.add(
            Persona(
                R.drawable.pr_bruce_willis,
                "Bruce Willis",
                "Butch Coolidge"
            )
        )
        lista.add(
            Persona(
                R.drawable.pr_tim_roth,
                "Tim Roth",
                "Pumpkin"
            )
        )

        return lista
    }

    private fun generarPersonasNaranja():ArrayList<Persona>{
        var lista = ArrayList<Persona>()

        lista.add(
            Persona(
                R.drawable.pr_malcom_mcdowell,
                "Malcolm McDowell",
                "Alex"
            )
        )
        lista.add(
            Persona(
                R.drawable.pr_patrick_magee,
                "Patrick Magee",
                "Mr Alexander"
            )
        )
        lista.add(
            Persona(
                R.drawable.pr_warren_clarke,
                "Warren Clarke",
                "Dim"
            )
        )

        lista.add(
            Persona(
                R.drawable.pr_clive_francis,
                "Clive Francis",
                "Lodger"
            )
        )
        lista.add(
            Persona(
                R.drawable.pr_miriam_karlin,
                "Miriam Karlin",
                "Catlady"
            )
        )

        return lista
    }

    private fun generarPersonasNotebook():ArrayList<Persona>{
        var lista = ArrayList<Persona>()

        lista.add(
            Persona(
                R.drawable.pr_gena_rowlands,
                "Gena Rowlands",
                "Allie Calhoun"
            )
        )
        lista.add(
            Persona(
                R.drawable.pr_ryan_gosling,
                "Ryan Gosling",
                "Noah"
            )
        )
        lista.add(
            Persona(
                R.drawable.pr_rachel_mcadams,
                "Rachel McAdams",
                "Allie"
            )
        )

        lista.add(
            Persona(
                R.drawable.pr_kevin_connolly,
                "Kevin Connolly",
                "Fin"
            )
        )
        lista.add(
            Persona(
                R.drawable.pr_james_garner,
                "James Garner",
                "Duke"
            )
        )

        return lista
    }

    private fun generarPersonasMatrix():ArrayList<Persona>{
        var lista = ArrayList<Persona>()

        lista.add(
            Persona(
                R.drawable.pr_keanu_reeves,
                "Keanu Reeves",
                "Neo"
            )
        )
        lista.add(
            Persona(
                R.drawable.pr_laurence_fishburne,
                "Laurence Fishburne",
                "Morpheus"
            )
        )
        lista.add(
            Persona(
                R.drawable.pr_carrie_ann_moss,
                "Carrie-Anne Moss",
                "Trinity"
            )
        )

        lista.add(
            Persona(
                R.drawable.pr_hugo_weaving,
                "Hugo Weaving",
                "Agent Smith"
            )
        )
        lista.add(
            Persona(
                R.drawable.pr_gloria_foster,
                "Gloria Foster",
                "Oracle"
            )
        )

        return lista
    }


}