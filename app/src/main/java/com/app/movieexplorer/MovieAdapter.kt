package com.app.movieexplorer

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class MovieAdapter(private  val movieList:ArrayList<Movie>):RecyclerView.Adapter<MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val  inflater=LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int =movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie:Movie=movieList[position]
        holder.bind(movie)
    }

}

class MovieViewHolder(inflater: LayoutInflater,parent:ViewGroup):RecyclerView.ViewHolder(inflater.inflate(R.layout.movies_item,parent,false)){

    private  var mImageView: ImageView? =null
    private  var mTitle:TextView? = null
    private  var mDate:TextView? = null

    init {
        mImageView=itemView.findViewById(R.id.movieImage)
        mTitle=itemView.findViewById(R.id.movieTitle)
        mDate=itemView.findViewById(R.id.movieDate)
    }

    fun bind(movie: Movie){
        mTitle?.text=movie.title
        mDate?.text=movie.releaseDate
        mImageView?.let { Glide.with(itemView.context).load("https://image.tmdb.org/t/p/original"+movie.posterPath).diskCacheStrategy(DiskCacheStrategy.ALL ).into(it) }
    }
}