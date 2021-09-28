package com.example.login.ui.clases

import java.io.Serializable

class ActorFireBase (val uid:String?,
                     var biografia:String?,
                     val nombre:String?,
                     val peliculas: String?,
                     val premios:String?,
                     val imagen:String?,
                     val personaje:String?,
): Serializable {
    override fun toString(): String {
        return "ActorFireBase(uid=$uid, biografia=$biografia, nombre=$nombre, peliculas=$peliculas, premios=$premios, imagen=$imagen, personaje=$personaje)"
    }
}