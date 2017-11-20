package com.joseluisquintana.data.OompaLoompa

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class OompaLoompa (@SerializedName("id") var id: Long = 0,
                        @SerializedName("first_name") var firstName: String? = null,
                        @SerializedName("last_name") var lastName: String? = null,
                        @SerializedName("description") var description: String? = null,
                        @SerializedName("gender") var gender: String? = null,
                        @SerializedName("image") var image: String? = null,
                        @SerializedName("profession") var profession: String? = null,
                        @SerializedName("email") var email: String? = null) : Parcelable {

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        firstName = parcel.readString()
        lastName = parcel.readString()
        description = parcel.readString()
        gender = parcel.readString()
        image = parcel.readString()
        profession = parcel.readString()
        email = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(description)
        parcel.writeString(gender)
        parcel.writeString(image)
        parcel.writeString(profession)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OompaLoompa> {
        override fun createFromParcel(parcel: Parcel): OompaLoompa {
            return OompaLoompa(parcel)
        }

        override fun newArray(size: Int): Array<OompaLoompa?> {
            return arrayOfNulls(size)
        }
    }
}
