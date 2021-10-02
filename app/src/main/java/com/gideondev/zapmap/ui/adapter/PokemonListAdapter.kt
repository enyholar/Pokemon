package com.gideondev.zapmap.ui.adapter

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Handler
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gideondev.zapmap.databinding.ItemPokeViewBinding
import com.gideondev.zapmap.databinding.ItemProgressLoadingBinding
import com.gideondev.zapmap.model.Pokemon
import com.gideondev.zapmap.utils.IMG_BASE_URL
import com.gideondev.zapmap.utils.VIEW_TYPE_ITEM
import com.gideondev.zapmap.utils.VIEW_TYPE_LOADING


class PokemonListAdapter(
    private val items: MutableList<Pokemon?>,
    private val listner: ClickListner
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    lateinit var mcontext: Context
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = ItemPokeViewBinding.inflate(inflater)
//        return ViewHolder(binding)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mcontext = parent.context
        return if (viewType == VIEW_TYPE_ITEM) {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemPokeViewBinding.inflate(inflater)
            ItemViewHolder(binding)
        } else {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemProgressLoadingBinding.inflate(inflater)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                binding.progressbar.indeterminateDrawable.colorFilter = BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
            } else {
                binding.progressbar.indeterminateDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY)
            }
            LoadingViewHolder(binding)
        }
    }

    fun addLoadingView() {
        //add loading item
        Handler().post {
            items.add(null)
            notifyItemInserted(items.size - 1)
        }
    }

    fun removeLoadingView() {
        //Remove loading item
        if (items.size != 0) {
            items.removeAt(items.size - 1)
            notifyItemRemoved(items.size)
        }
    }

    class ItemViewHolder(val binding: ItemPokeViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Pokemon?, position: Int) {
            with(binding) {
                binding.txtPokeName.text = item?.name ?: ""
                binding.txtPokeUrl.text = item?.url ?: ""
                val imgNum = position + 1
                val media = "$IMG_BASE_URL$imgNum.png"
                        Glide.with(binding.imgPokemon .context)
                            .load(media)
                            .centerCrop()
                            .into(binding.imgPokemon)
            }
        }
    }

    class LoadingViewHolder(itemView: ItemProgressLoadingBinding) : RecyclerView.ViewHolder(itemView.root)

    override fun getItemCount(): Int = items!!.size

    fun addItem(model: Pokemon) {
        if (items != null) {
            items.add(model)
        }
        if (items != null) {
            notifyItemInserted(items.size)
        }
    }

        fun addAllItem(pokemonList: MutableList<Pokemon>) {
            // items.clear()
            if (items != null) {
                items.addAll(pokemonList)
            }
            notifyDataSetChanged()
        }


    fun removeAt(position: Int) {
        if (items != null) {
            items.removeAt(position)
        }
        notifyItemRemoved(position)
    }

//    override fun onBindViewHolder(
//        holder: ViewHolder,
//        position: Int
//    ) {
//        val model: Pokemon? = items!![position]
//
//        holder.itemView .setOnClickListener(View.OnClickListener {
//            listner.onItemClick(model)
//        })
//        holder.bind(model)
//    }

    inner class ViewHolder(val binding: ItemPokeViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Pokemon?) {
            with(binding) {
              binding.txtPokeName.text = item?.name ?: ""
                binding.txtPokeUrl.text = item?.url ?: ""

            }
        }

    }

    interface ClickListner {
        fun onItemClick(model: Pokemon?, position: Int)
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] == null) {
           VIEW_TYPE_LOADING
        } else {
        VIEW_TYPE_ITEM
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == VIEW_TYPE_ITEM) {
            if (holder is ItemViewHolder) {
                val model: Pokemon? = items!![position]

                holder.itemView .setOnClickListener(View.OnClickListener {
                    listner.onItemClick(model, position)
                })
                holder.bind(model,position)
            }
        }
    }
}