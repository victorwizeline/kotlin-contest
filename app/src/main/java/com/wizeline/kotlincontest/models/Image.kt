package com.wizeline.kotlincontest.models

import com.google.gson.annotations.SerializedName

data class Image(
        @SerializedName("#text")
        var url: String,
        var size: String
)