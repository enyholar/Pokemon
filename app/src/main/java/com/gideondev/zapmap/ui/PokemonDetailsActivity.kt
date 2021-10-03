package com.gideondev.zapmap.ui
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.gideondev.zapmap.R
import com.gideondev.zapmap.base.BaseActivity
import com.gideondev.zapmap.databinding.ActivityPokemonDetailsBinding
import com.gideondev.zapmap.model.Pokemon
import com.gideondev.zapmap.model.details.PokemonDetailsResponse
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
        viewModel.pokemonDetailsResponse
            .observe(this, Observer {
                it?.let {
                    Log.e("pokemon response", "response : ${it.toString()}")
                    print(it.toString())
                    setValueToView(it)

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

    fun setValueToView(model: PokemonDetailsResponse){
        binding.txtHeight.text = model.height.toString()
        binding.txtWeight.text = model.weight.toString()
        binding.txtPokeName.text = model.name.toString()
        val abilitiesList: MutableList<String> = ArrayList<String>()
        val speciesList: MutableList<String> = ArrayList<String>()

        model.abilities.forEach{
          abilitiesList.add(it.ability.name)
        }
        model.types.forEach{
            speciesList.add(it.type.name)
        }
        val ability = abilitiesList.joinToString(separator = ",")
        val species = speciesList.joinToString(separator = ",")
        binding.txtAbilities.text = ability
        binding.txtSpecies.text = species
        Glide.with(binding.imgPokeMan .context)
            .load(model.sprites.front_default)
            .centerCrop()
            .into(binding.imgPokeMan)
    }

    private fun fetchPokemonDetails(id: Int){
        viewModel.getPokemonDetailsFromServer(id)
    }
}