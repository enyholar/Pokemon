package com.gideondev.zapmap.viewModel
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gideondev.zapmap.model.PokeMonListResponse
import com.gideondev.zapmap.model.details.PokemonDetailsResponse
import io.reactivex.disposables.CompositeDisposable


open class BaseViewModel : ViewModel() {

    val showProgress: ObservableField<Boolean> = ObservableField()
    val networkError: ObservableField<Boolean> = ObservableField()
    val commonMessage: MutableLiveData<String> = MutableLiveData()
    val dataLoaded: ObservableField<Boolean> = ObservableField()
    val isLoading: MutableLiveData<Boolean?> = MutableLiveData()
    val errorMessage: MutableLiveData<String?> = MutableLiveData()
    val pokeMonListResponse: MutableLiveData<PokeMonListResponse?> = MutableLiveData()
    val pokemonDetailsResponse: MutableLiveData<PokemonDetailsResponse?> = MutableLiveData()


    protected var disposable: CompositeDisposable = CompositeDisposable()


    override fun onCleared() {
        this.disposable.dispose()
        super.onCleared()
    }


    init {
        showProgress.set(false)
        networkError.set(false)
    }

    open fun tryAgainFunction() {

    }

    open fun showProgressBar() {
        showProgress.set(true)
    }

    open fun hideProgressBar() {
        showProgress.set(false)

    }

    open fun showConnectionError() {
        hideProgressBar()
        networkError.set(true)
    }

    open fun hideConnectionError() {
        networkError.set(false)
    }
}