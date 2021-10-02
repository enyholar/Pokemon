package com.gideondev.zapmap.model.details

import com.google.gson.annotations.SerializedName

data class Other(
    val dream_world: DreamWorld,
    @field:SerializedName("official-artwork")
    val official_artwork: OfficialArtwork
)