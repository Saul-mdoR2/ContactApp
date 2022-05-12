package com.r2devpros.contactapp.model

import android.os.Parcel
import android.os.Parcelable

data class Person(
    val image: String,
    val name: String,
    val age: Int,
    val genre: String,
    val phoneNumber: String,
    val mobileNumber: String,
    val email: String
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(name)
        parcel.writeInt(age)
        parcel.writeString(genre)
        parcel.writeString(phoneNumber)
        parcel.writeString(mobileNumber)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }
}
