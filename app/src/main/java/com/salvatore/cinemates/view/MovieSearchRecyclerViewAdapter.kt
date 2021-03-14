package com.salvatore.cinemates.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.salvatore.cinemates.MainActivity
import com.salvatore.cinemates.R
import com.salvatore.cinemates.model.MovieSearchResultDto

class MovieSearchRecyclerViewAdapter(private val dataSet: ArrayList<MovieSearchResultDto>, private val mainActivity: MainActivity): RecyclerView.Adapter<MovieSearchRecyclerViewViewHolder>() {
    private val TAG = "MovieSearchRecyclerViewAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchRecyclerViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_movie_search_result, parent, false)
        return MovieSearchRecyclerViewViewHolder(view, mainActivity)
    }

    override fun onBindViewHolder(holder: MovieSearchRecyclerViewViewHolder, position: Int) {
        holder.bindItemToViewHolder(dataSet[position])
    }

    fun updateDataset(updatedDataSet: ArrayList<MovieSearchResultDto>) {
        this.dataSet.clear()
        this.dataSet.addAll(updatedDataSet)
        notifyDataSetChanged()
    }

    override fun getItemCount() = dataSet.size
}

 /*

fun <T: RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit) : T {
    itemView.setOnClickListener {
        event.invoke(adapterPosition, itemViewType)
    }
    return this
}

 */