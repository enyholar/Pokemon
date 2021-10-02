package com.gideondev.zapmap.ui
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gideondev.zapmap.R
import com.gideondev.zapmap.base.BaseActivity
import com.gideondev.zapmap.databinding.ActivityPokemonDetailsBinding
import com.gideondev.zapmap.viewModel.PokeViewModel

class PokemonDetailsActivity : BaseActivity() {
    private lateinit var binding: ActivityPokemonDetailsBinding
    lateinit var viewModel: PokeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pokemon_details)
    }


    override fun configureDesign() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PokeViewModel::class.java)
        val  pokemonId = intent.getIntExtra("id",0)
        observeData()
        fetchPokemonDetails(pokemonId + 1)
    }

    private fun observeData(){
        viewModel.pokeMonListResponse
            .observe(this, Observer {
                it?.let {
                    Log.e("pokemon response", "response : ${it.toString()}")
                    print(it.toString())

                }

            })
        viewModel.isLoading
            .observe(this, Observer {
                it?.let {
//                this.updateRefreshLayout(it)
                }
            })
        viewModel.errorMessage
            .observe(this, Observer {
                it?.let {
                    Log.e("TAG", "Throw an error : $it")
                }
            })
    }

    private fun fetchPokemonDetails(id: Int){
        viewModel.getPokemonDetailsFromServer(id)
    }
}