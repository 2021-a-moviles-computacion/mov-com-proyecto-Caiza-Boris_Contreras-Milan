package com.example.replicacionimdb.ui.clases

import android.os.Parcel
import android.os.Parcelable

class Persona (
    val ImagePersona:Int,
    val Nombre:String?,
    val NombrePersonaje:String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ImagePersona)
        parcel.writeString(Nombre)
        parcel.writeString(NombrePersonaje)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Persona> {
        override fun createFromParcel(parcel: Parcel): Persona {
            return Persona(parcel)
        }

        override fun newArray(size: Int): Array<Persona?> {
            return arrayOfNulls(size)
        }
    }

}