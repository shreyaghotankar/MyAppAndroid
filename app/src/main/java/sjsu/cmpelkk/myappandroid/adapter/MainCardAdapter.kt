package sjsu.cmpelkk.myappandroid.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import sjsu.cmpelkk.myappandroid.DetailScrollingActivity
import sjsu.cmpelkk.myappandroid.R

class MainCardAdapter (
    private var titles: List<String>,
    private var descriptions: List<String>,
    private var urlToImages: List<String>,
    private var urls: List<String>,
    private var authors: List<String>
) : RecyclerView.Adapter<MainCardAdapter.ViewHolder>() {

    inner class ViewHolder(cardView: CardView) : RecyclerView.ViewHolder(cardView){

        val title: TextView = cardView.findViewById(R.id.carditemtitletextView)
        val detail: TextView = cardView.findViewById(R.id.carditemdetailtext)
        val image: ImageView = cardView.findViewById(R.id.cardimageView)

        init {

            cardView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                val context = cardView.context

                //on click - go to the webpage outside the app
                /*val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(urls[position])
                startActivity(cardView.context, intent, null)*/

                //on click - show the description and url link
                val intent = Intent(context, DetailScrollingActivity::class.java).apply {
                    putExtra("dataTitle", Uri.parse(titles[position]))
                    putExtra("dataDesc", Uri.parse(descriptions[position]))
                    putExtra("dataImage", Uri.parse(urlToImages[position]))
                    putExtra("dataUrl", Uri.parse(urls[position]))
                    putExtra("dataAuthor", Uri.parse(authors[position]))

                //on click - load the news url in the web activity
                /*val intent = Intent(context, WebActivity::class.java).apply {
                    putExtra("url", Uri.parse(urls[position]))*/
                }

                context.startActivity(intent)
            }

        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        TODO("Not yet implemented")
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater
            .inflate(R.layout.card_item_view, parent, false) as CardView

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
//        TODO("Not yet implemented")

        return titles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO("Not yet implemented")

        holder.title.text = titles[position]
        holder.detail.text = descriptions[position]

        Glide.with(holder.image)
            .load(urlToImages[position])
            .into(holder.image)
    }



}