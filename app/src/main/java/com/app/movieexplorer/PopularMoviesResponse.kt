package com.app.movieexplorer

import com.google.gson.annotations.SerializedName

class PopularMoviesResponse{

    @SerializedName("page")
    var page:String?=null

    @SerializedName("total_results")
    var totalResults:Int=0

    @SerializedName("total_pages")
    var totalPages:Int=0

    @SerializedName("results")
    var results=ArrayList<Movie>()
}

class Movie{

    @SerializedName("popularity")
    var popularity:Float=0.toFloat()

    @SerializedName("vote_count")
    var voteCount:Int=0

    @SerializedName("video")
    var video:Boolean=false

    @SerializedName("poster_path")
    var posterPath:String?=null

    @SerializedName("id")
    var id:Int=0

    @SerializedName("adult")
    var adult:Boolean=false

    @SerializedName("backdrop_path")
    var backdropPath:String?=null

    @SerializedName("original_language")
    var originalLanguge:String?=null

    @SerializedName("original_title")
    var originalTitle:String?=null

    @SerializedName("title")
    var title:String?=null

    @SerializedName("overview")
    var overview:String?=null

    @SerializedName("release_date")
    var releaseDate:String?=null

    @SerializedName("vote_average")
    var voteAverage:Float=0.toFloat()

}