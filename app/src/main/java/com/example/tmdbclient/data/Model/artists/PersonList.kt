package com.example.tmdbclient.data.Model.artists


import com.google.gson.annotations.SerializedName

data class PersonList(
    @SerializedName("results")
    val people: List<Person>
)
