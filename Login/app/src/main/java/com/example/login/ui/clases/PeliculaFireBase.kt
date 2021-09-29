package com.example.login.ui.clases

import java.io.Serializable

class PeliculaFireBase (val uid:String?,
                        var IdYouTube:String?,
                        val ano: String?,
                        val calificacion:Double?,
                        var categorias: String?,
                        val clasificacion: String?,
                        var dondeVer:String?,
                        val duracion: String?,
                        val nombre:String?,
                        val portadaLink:String?,
                        var portadaTrailer:String?,
                        var reparto:String?,
                        var sinopsis:String?

): Serializable {

    override fun toString(): String {
        return "PeliculaFireBase(uid=$uid, IdYouTube=$IdYouTube, ano=$ano, calificacion=$calificacion, categorias=$categorias, clasificacion=$clasificacion, dondeVer=$dondeVer, duracion=$duracion, nombre=$nombre, portadaLink=$portadaLink, portadaTrailer=$portadaTrailer, reparto=$reparto, sinopsis=$sinopsis)"
    }
}