package com.example.tmdbclient.data.model.artists


import com.google.gson.annotations.SerializedName

data class PersonList(
    @SerializedName("results")
    val people: List<Person>
)
