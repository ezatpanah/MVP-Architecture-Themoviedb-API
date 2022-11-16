package com.ezatpanah.themoviedb_api_mvp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.ezatpanah.themoviedb_api_mvp.databinding.ItemMoviesCommonBinding
import com.ezatpanah.themoviedb_api_mvp.response.CommonMoviesListResponse
import com.ezatpanah.themoviedb_api_mvp.utils.Constants.POSTER_BASE_URL
import javax.inject.Inject

class CommonMoviesAdapter @Inject constructor() : PagingDataAdapter<CommonMoviesListResponse.Result,CommonMoviesAdapter.ViewHolder>(differCallback) {

    private lateinit var binding: ItemMoviesCommonBinding
    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMoviesCommonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(getItem(position)!!)
        holder.setIsRecyclable(false)
    }


    inner class ViewHolder() : RecyclerView.ViewHolder(binding.root) {
        fun setData(item: CommonMoviesListResponse.Result) {
            binding.apply {
                movieNameTxt.text = item.title
                movieRateTxt.text = item.voteAverage.toString()
                movieYearTxt.text = item.releaseDate
                movieCountryTxt.text = item.originalLanguage

                val moviePosterURL = POSTER_BASE_URL + item.posterPath
                moviePosterImg.load(moviePosterURL) {
                    crossfade(true)
                    crossfade(800)
                    scale(Scale.FIT)
                }
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }

    private var onItemClickListener : ((CommonMoviesListResponse.Result) -> Unit)? = null
    fun setonItemClickListener(listener: (CommonMoviesListResponse.Result) -> Unit) {
        onItemClickListener=listener
    }

//    fun bind(data:List<CommonMoviesListResponse>){
//        val moviesDiffUtils = FavoriteMoviesAdapter.MoviesDiffUtils(moviesList, data)
//        val diffUtils = DiffUtil.calculateDiff(moviesDiffUtils)
//        moviesList=data
//        diffUtils.dispatchUpdatesTo(this)
//    }

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<CommonMoviesListResponse.Result>() {
            override fun areItemsTheSame(oldItem: CommonMoviesListResponse.Result, newItem: CommonMoviesListResponse.Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CommonMoviesListResponse.Result, newItem: CommonMoviesListResponse.Result): Boolean {
                return oldItem == newItem
            }
        }
    }

}