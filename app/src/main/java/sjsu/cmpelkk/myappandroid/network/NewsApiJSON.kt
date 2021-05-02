package sjsu.cmpelkk.myappandroid.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsApiJSON(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)