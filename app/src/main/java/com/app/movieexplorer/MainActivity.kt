package com.app.movieexplorer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var moviesListItems: ArrayList<Movie> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //findViewById<View>(R.id.test).setOnClickListener { getData() }

        movieList.apply{
            layoutManager= GridLayoutManager(this@MainActivity,2)
            adapter=MovieAdapter(moviesListItems)
        }
        getData()

    }

    private fun getData(){

        val client=OkHttpClient.Builder()
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)

        if(BuildConfig.DEBUG){
            val logging=HttpLoggingInterceptor()
            logging.level=HttpLoggingInterceptor.Level.BASIC
            client.addInterceptor(logging)
        }

        val retrofit=Retrofit.Builder()
            .baseUrl(BaseUrl)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service=retrofit.create(MovieService::class.java)
        val call=service.getPopularMoviesList(AppId, language, page)

        call.enqueue(object:Callback<PopularMoviesResponse>{
            override fun onFailure(call: Call<PopularMoviesResponse>?, t: Throwable?) {
                Log.d("", t?.message) //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(
                call: Call<PopularMoviesResponse>?,
                response: Response<PopularMoviesResponse>?
            ) {
                if(response?.code()==200){
                    val popMovies=response.body()
                    moviesListItems.addAll(popMovies.results)
                    movieList.adapter?.notifyDataSetChanged()
                }
            }

        })
    }

    companion object {

        var BaseUrl = "https://api.themoviedb.org/3/"
        var AppId = "8aabe57ac95a467244fca5ea7aeb658b"
        var language = "en-US"
        var page = "1"
    }
}
