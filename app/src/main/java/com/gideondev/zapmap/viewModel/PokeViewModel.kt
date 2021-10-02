package com.gideondev.zapmap.viewModel

import com.gideondev.zapmap.di.OBSERVER_ON
import com.gideondev.zapmap.di.SUBCRIBER_ON
import com.gideondev.zapmap.repository.PokeRepository
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named


class PokeViewModel
@Inject
constructor(
    private val pokeRepository: PokeRepository,
    @param:Named(SUBCRIBER_ON)  val subscriberOn: Scheduler,
    @param:Named(OBSERVER_ON)  val observerOn: Scheduler
) : BaseViewModel() {
    val TAG = PokeViewModel::class.java.name


    fun getPokeMonListFromServer(offset: Int, limit: Int) {
        disposable.addAll(this.pokeRepository.getPokemonList(offset, limit)
            .subscribeOn(subscriberOn)
            .observeOn(observerOn)
            .doOnSubscribe { isLoading.value = true }
            .doOnComplete { isLoading.value = false }
            .doOnError { isLoading.value = false }
            .subscribe({ pokeMonListResponse.value = it },{ errorMessage.value = it.message })
        )
    }

    fun getPokemonDetailsFromServer(id: Int) {
        disposable.addAll(this.pokeRepository.getPokemonDetails(id)
            .subscribeOn(subscriberOn)
            .observeOn(observerOn)
            .doOnSubscribe { isLoading.value = true }
            .doOnComplete { isLoading.value = false }
            .doOnError { isLoading.value = false }
            .subscribe({ pokemonDetailsResponse.value = it },{ errorMessage.value = it.message })
        )
    }
}