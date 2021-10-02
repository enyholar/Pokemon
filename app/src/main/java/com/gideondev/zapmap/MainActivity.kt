package com.gideondev.zapmap
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gideondev.zapmap.base.BaseActivity
import com.gideondev.zapmap.databinding.ActivityMainBinding
import com.gideondev.zapmap.model.Pokemon
import com.gideondev.zapmap.ui.PokemonDetailsActivity
import com.gideondev.zapmap.ui.adapter.PokemonListAdapter
import com.gideondev.zapmap.utils.OnLoadMoreListener
import com.gideondev.zapmap.utils.RecyclerViewLoadMoreScroll
import com.gideondev.zapmap.viewModel.PokeViewModel

class MainActivity :  BaseActivity(){
    private var isLoadMore: Boolean = false
    private lateinit var scrollListener: RecyclerViewLoadMoreScroll
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var pokemonAdapter: PokemonListAdapter
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: PokeViewModel
    private var pokemonList: MutableList<Pokemon?> = ArrayList<Pokemon?>()
    var offset = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
     setUpAdapter()
        setRVScrollListener()
    }


    override fun configureDesign() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PokeViewModel::class.java)
        observeData()
        fetchPokeMonList(0,20)
    }

    private fun fetchPokeMonList(offset: Int, limit: Int){
        viewModel.getPokeMonListFromServer(offset, limit)
    }
    private fun observeData(){
        viewModel.pokeMonListResponse
            .observe(this, Observer {
                it?.let {
                    Log.e("pokemon response", "response : ${it.toString()}")
                    print(it.toString())
                    if (isLoadMore){
                        pokemonAdapter.removeLoadingView()
                        scrollListener.setLoaded()
                    }
                    pokemonAdapter.addAllItem(it.pokemon.toMutableList())

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

    private fun setUpAdapter() {
        layoutManager = LinearLayoutManager(this)
        binding.rvCategory.layoutManager = layoutManager
        pokemonAdapter = PokemonListAdapter(pokemonList, object  : PokemonListAdapter.ClickListner{

            override fun onItemClick(model: Pokemon?, position: Int) {
                val intent = Intent(this@MainActivity, PokemonDetailsActivity::class.java)
                intent.putExtra("id",position)
                startActivity(intent)
            }

        })
        binding.rvCategory.setHasFixedSize(true)
        binding.rvCategory.adapter = pokemonAdapter

    }

    private  fun setRVScrollListener() {
        layoutManager = LinearLayoutManager(this)
        scrollListener = RecyclerViewLoadMoreScroll(layoutManager as LinearLayoutManager)
        scrollListener.setOnLoadMoreListener(object :
            OnLoadMoreListener {
            override fun onLoadMore() {
                offset += 20
                isLoadMore = true
                pokemonAdapter.addLoadingView()
              fetchPokeMonList(offset,20)
            }
        })
        binding.rvCategory.addOnScrollListener(scrollListener)
    }
}