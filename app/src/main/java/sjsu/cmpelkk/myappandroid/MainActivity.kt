package sjsu.cmpelkk.myappandroid

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sjsu.cmpelkk.myappandroid.adapter.MainCardAdapter
import sjsu.cmpelkk.myappandroid.myutil.SwipeToDeleteCallback
import sjsu.cmpelkk.myappandroid.network.NewsApi
import sjsu.cmpelkk.myappandroid.network.NewsApiJSON


import sjsu.cmpelkk.myappandroid.network.NewsApiService
import java.io.Serializable
import java.lang.Exception

const val POST_REQUEST_CODE = 32
const val BASE_URL = "https://newsapi.org"

class MainActivity : AppCompatActivity() {
    var datalist: MutableList<DataItem> = mutableListOf()
    lateinit var recyclerCard: RecyclerView

    private var titlesList =  mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<String>()
    private var urlsList = mutableListOf<String>()
    private var authorsList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // network call
        makeAPIRequest()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean{
        menuInflater.inflate(R.menu.menu_search_item, menu)

        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        searchView.maxWidth = Int.MAX_VALUE
        searchView.inputType = InputType.TYPE_CLASS_TEXT
        searchView.queryHint = "Search News"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // TODO("Not yet implemented")
                searchRequest(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //TODO("Not yet implemented")

                return false
            }

        })

        searchView.setOnCloseListener(object: SearchView.OnCloseListener {
            override fun onClose(): Boolean {
               // TODO("Not yet implemented")
                val intent = Intent(this@MainActivity, MainActivity::class.java)
                startActivity(intent)

                return true
            }

        })



        return super.onCreateOptionsMenu(menu)
    }

    // Retrofit with Gson
    private fun searchRequest(query: String?) {

        val q = query.toString()

        titlesList.clear()
        descList.clear()
        imagesList.clear()
        urlsList.clear()
        authorsList.clear()

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)

        GlobalScope.launch(Dispatchers.IO){
            try{
                val response = api.searchNews(q)
               // Log.d("search", "results: + $response")
                for(article in response.articles) {
                   // Log.d("MainActivity", "Results + $article")

                    addToList(article.title,
                        article.description.toString(),
                        article.urlToImage.toString(),
                        article.url,
                        article.author.toString())
                }
                withContext(Dispatchers.Main){
                    setupRecyclerView()
                }


            } catch (e: Exception){
                Log.e("MainActivity", e.toString())
            }

        }
    }

    //Retrofit with Moshi
    private fun makeAPIRequest() {

        GlobalScope.launch(Dispatchers.IO) {
            try {

                NewsApi.retrofitService.getNews().enqueue(
                    object: Callback<NewsApiJSON> {
                        override fun onFailure(call: Call<NewsApiJSON>, t: Throwable) {
                            //TODO("Not yet implemented")
                            //Log.d("MainActivity", "Failure: " + t.message)
                        }

                        override fun onResponse(
                            call: Call<NewsApiJSON>,
                            response: Response<NewsApiJSON>
                        ) {
                           val resp = response.body()
                            //TODO("Not yet implemented")
                            //Log.d("MainActivity", "response :  $resp")
                            if (resp != null) {
                                for(article in resp.articles){
                                    //Log.d("MainActivity", "check article: " + article.description.toString())
                                    addToList(article.title,
                                        article.description.toString(),
                                        article.urlToImage.toString(),
                                        article.url, article.author.toString())
                                }
                                setupRecyclerView()

                            }
                        }

                    }

                )


            } catch (e: Exception) {
                Log.e("MainActivity", e.toString())
            }

        }

    }

    private fun addToList(title: String, description: String, image: String, url: String, author:String) {
        titlesList.add(title)
        descList.add(description)
        imagesList.add(image)
        urlsList.add(url)
        authorsList.add(author)

    }

    private fun setupRecyclerView() {
        val adapter = MainCardAdapter(titlesList, descList, imagesList, urlsList, authorsList)
        val recyclerView: RecyclerView = findViewById(R.id.cardrecyclerview)
        recyclerView.adapter = adapter


    }


}
