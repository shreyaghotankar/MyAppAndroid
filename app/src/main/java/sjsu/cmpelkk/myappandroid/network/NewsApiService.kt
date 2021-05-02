package sjsu.cmpelkk.myappandroid.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://newsapi.org"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .add(NullToEmptyStringAdapter())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface NewsApiService {
    @GET("/v2/top-headlines?country=us&apiKey=53f2fc2e76374a44a76614b212a378ea")
     fun getNews(): Call<NewsApiJSON>

    @GET("/v2/everything")
     suspend fun searchNews(
            @Query("q")
            searchQuery: String,
            @Query("apiKey")
            apiKey: String = "53f2fc2e76374a44a76614b212a378ea"
    ) : NewsApiJSON
}


object NewsApi{
    val retrofitService: NewsApiService by lazy{
        retrofit.create(NewsApiService::class.java)
    }
}
