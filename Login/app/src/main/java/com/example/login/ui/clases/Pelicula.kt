package com.example.login.ui.clases

import java.io.Serializable

class Pelicula ( val ImagePortada:Int,
                 val ImageTrailer:Int,
                 val Title: String?,
                 val Calificacion:String?,
                 val Ano:String?,
                 val Clasificacion:String?,
                 val Duracion:String?,
                 val Sinopsis:String?,
                 val Director:String?,
                 val Guionistas:String?,
                 val Categorias: ArrayList<String>,
                 val Actores: ArrayList<Persona>

): Serializable {

}