package com.gideondev.zapmap.network
import com.gideondev.zapmap.model.PokeMonListResponse
import com.gideondev.zapmap.model.details.PokemonDetailsResponse
import io.reactivex.Observable
import retrofit2.http.*


interface PokeApiInterface {

    @GET("pokemon")
    fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,

    ): Observable<PokeMonListResponse>

    @GET("pokemon/{id}")
    fun getPokemonDetails(
        @Path("id") id: Int,

    ): Observable<PokemonDetailsResponse>

}