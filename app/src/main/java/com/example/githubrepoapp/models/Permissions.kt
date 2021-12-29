package com.example.githubrepoapp.models


import com.google.gson.annotations.SerializedName

data class Permissions(
    @SerializedName("admin")
    var admin: Boolean?,
    @SerializedName("maintain")
    var maintain: Boolean?,
    @SerializedName("pull")
    var pull: Boolean?,
    @SerializedName("push")
    var push: Boolean?,
    @SerializedName("triage")
    var triage: Boolean?
)