package com.example.replicacionimdb.ui.pelicula

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavType
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.replicacionimdb.R
import com.example.replicacionimdb.ui.adapters.PersonaAdapter
import com.example.replicacionimdb.ui.adapters.categoriaAdapter
import com.example.replicacionimdb.ui.clases.Persona

/**
 * A simple [Fragment] subclass.
 * Use the [ActividadDetallePelicula.newInstance] factory method to
 * create an instance of this fragment.
 */
class ActividadDetallePelicula : Fragment(

) {




    private var imagenPortadaData: Int?= null
    private var imagenTrailerData: Int?= null
    private var tituloData: String?= null
    private var calificacionData: String?= null
    private var anoData: String?= null
    private var descripcionData: String?= null
    private var sinopsisData: String?= null
    private var directorData: String?= null
    private var guionistasData: String?= null
    private var categoriasData: ArrayList<String>?= null
    private var repartoData: ArrayList<Persona>?= null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_actividad_detalle_pelicula, container, false)


        arguments?.let{
            tituloData=it.getString("Titulo")
            imagenPortadaData=it.getInt("Portada")
            imagenTrailerData=it.getInt("Trailer")
            calificacionData=it.getString("Calificacion")
            anoData=it.getString("Ano")
            descripcionData=it.getString("Descripcion")
            sinopsisData=it.getString("Sinopsis")
            directorData=it.getString("Director")
            guionistasData=it.getString("Guionistas")
            categoriasData=it.getStringArrayList("Categorias")
            repartoData=it.getParcelableArrayList("Reparto")

        }


        //Nombre pelicula
        var titulo1 = root.findViewById<TextView>(R.id.textView7)
        titulo1.text = tituloData

        //imagen portada
        var portada = root.findViewById<ImageView>(R.id.Iv_pelicula_portada2)
        portada.setImageResource(imagenPortadaData!!)

        //Imagen trailer
        var imagenPeliculaTrailer = root.findViewById<ImageView>(R.id.imageView)
        imagenPeliculaTrailer.setImageResource(imagenTrailerData!!)

        //Calificacion
        var calificacionPelicula = root.findViewById<TextView>(R.id.textView10)
        calificacionPelicula.text = calificacionData

        //Descripcion trailer
        var descipcionTrailer = root.findViewById<TextView>(R.id.textView8)
        descipcionTrailer.text = tituloData+" ("+anoData+")"

        //Descripcion (edad bla bla bla)
        var descripcion = root.findViewById<TextView>(R.id.textView5)
        descripcion.text = descripcionData

        //sinopsis
        var sinopsis =  root.findViewById<TextView>(R.id.textView9)
        sinopsis.text = sinopsisData

        //director
        var director = root.findViewById<TextView>(R.id.TV_director)
        director.text=directorData

        //Guionistas
        var guionistas = root.findViewById<TextView>(R.id.TV_guionistas)
        guionistas.text=guionistasData



        var categorias = root.findViewById<RecyclerView>(R.id.recyclerView)
        categorias.adapter = categoriaAdapter(categoriasData!!)
        categorias.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)


        var reparto = root.findViewById<RecyclerView>(R.id.recyclerView2)
        reparto.adapter = PersonaAdapter(repartoData!!)
        reparto.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //var ImagenTrailer = R.id.imageView
        //ImagenTrailer.setImageResource(savedInstanceState!!.getInt("Imagen"))
    }

}