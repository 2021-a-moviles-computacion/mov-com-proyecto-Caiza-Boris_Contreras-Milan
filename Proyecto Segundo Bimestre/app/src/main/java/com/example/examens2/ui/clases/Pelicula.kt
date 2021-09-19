package com.example.examens2.ui.clases

import java.io.Serializable

class Pelicula ( val ImagePortada:Int,
                 val ImageTrailer:Int,
                 val Title: String?,
                 val Calificacion:String?,
                 val Ano:String?,
                 val Descripcion:String?,
                 val Sinopsis:String?,
                 val Director:String?,
                 val Guionistas:String?,
                 val Categorias: ArrayList<String>,
                 val Actores: ArrayList<Persona>

):Serializable{

}