package com.gideondev.zapmap.model

import com.google.gson.annotations.SerializedName

data class PokeMonListResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    @field:SerializedName("results")
    val pokemon: List<Pokemon>
)