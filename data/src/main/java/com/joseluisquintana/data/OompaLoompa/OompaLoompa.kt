package com.joseluisquintana.data.OompaLoompa

import com.google.gson.annotations.SerializedName

class OompaLoompa {
    @SerializedName("id") var id: Long = 0
    @SerializedName("first_name") var firstName: String? = null
    @SerializedName("last_name") var lastName: String? = null
    @SerializedName("description") var description: String? = null
    @SerializedName("gender") var gender: String? = null
    @SerializedName("image") var image: String? = null
    @SerializedName("profession") var profession: String? = null
    @SerializedName("email") var email: String? = null
}
