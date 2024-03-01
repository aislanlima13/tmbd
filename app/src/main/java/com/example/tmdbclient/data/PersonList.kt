package com.example.tmdbclient.data


import com.google.gson.annotations.SerializedName

data class PersonList(
    @SerializedName("results")
    val people: List<Person>
)
