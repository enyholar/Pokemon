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
    private val loginRepository: PokeRepository,
    @param:Named(SUBCRIBER_ON)  val subscriberOn: Scheduler,
    @param:Named(OBSERVER_ON)  val observerOn: Scheduler
) : BaseViewModel() {
    val TAG = PokeViewModel::class.java.name


//    fun loginUser(request: LoginRequest) {
//        disposable.addAll(this.loginRepository.loginUser(request)
//            .subscribeOn(subscriberOn)
//            .observeOn(observerOn)
//            .doOnSubscribe { isLoading.value = true }
//            .doOnComplete { isLoading.value = false }
//            .doOnError { isLoading.value = false }
//            .subscribe({ loginResponse.value = it },{ errorMessage.value = it.message })
//        )
//    }
}