package com.gideondev.zapmap.di
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gideondev.zapmap.di.viewModelFactory.ViewModelFactory
import com.gideondev.zapmap.viewModel.PokeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ActivityViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PokeViewModel::class)
    internal abstract fun postPokeViewModel(viewModel: PokeViewModel): ViewModel

}