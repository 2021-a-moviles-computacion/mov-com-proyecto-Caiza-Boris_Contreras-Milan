package com.example.replicacionimdb.ui.clases

import android.os.Parcel
import android.os.Parcelable

class Pelicula(
    val ImagePortada:Int,
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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        arrayListOf<String>().apply {
            parcel.readArrayList(String::class.java.classLoader)
        },
        arrayListOf<Persona>().apply {
            parcel.readArrayList(Persona::class.java.classLoader)
        }
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ImagePortada)
        parcel.writeInt(ImageTrailer)
        parcel.writeString(Title)
        parcel.writeString(Calificacion)
        parcel.writeString(Ano)
        parcel.writeString(Descripcion)
        parcel.writeString(Sinopsis)
        parcel.writeString(Director)
        parcel.writeString(Guionistas)
        parcel.writeList(Categorias)
        parcel.writeList(Actores)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pelicula> {
        override fun createFromParcel(parcel: Parcel): Pelicula {
            return Pelicula(parcel)
        }

        override fun newArray(size: Int): Array<Pelicula?> {
            return arrayOfNulls(size)
        }
    }


}