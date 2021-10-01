package com.gideondev.zapmap.di
import com.gideondev.zapmap.network.PokeApiInterface
import com.gideondev.zapmap.repository.PokeRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module
class RepositoryModule  {
    @Provides
    @Singleton
    fun provideRegisterRepository(pokeApiInterface: PokeApiInterface) = PokeRepository(pokeApiInterface)

}