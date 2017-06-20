package com.wizeline.kotlincontest.models

data class Album(
        var name: String,
        var mbid: String,
        var url: String,
        var artist: Artist,
        var image: List<Image>
)