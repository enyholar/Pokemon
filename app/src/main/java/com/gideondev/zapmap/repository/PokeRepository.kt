package com.gideondev.zapmap.repository

import com.gideondev.zapmap.network.PokeApiInterface



class PokeRepository(
    private val pokeApiInterface: PokeApiInterface
){

    fun getPokemonList(offset: Int, limit: Int) = pokeApiInterface.getPokemonList(offset, limit)
    fun getPokemonDetails(id: Int) = pokeApiInterface.getPokemonDetails(id)

}