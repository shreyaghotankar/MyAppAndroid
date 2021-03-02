package sjsu.cmpelkk.myappandroid

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import java.io.Serializable


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerCard: RecyclerView = findViewById(R.id.cardrecyclerview)
        recyclerCard.adapter = MainCardAdapter(carddefaultdata)

    }
}

class MainCardViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView) {
    val title: TextView = cardView.findViewById(R.id.carditemtitletextView)
    val story: TextView = cardView.findViewById(R.id.carditemdetailtext)
    val image: ImageView = cardView.findViewById(R.id.cardimageView)

    fun bind(oneitem: DataItem) {
        title.text = oneitem.title
        story.text = oneitem.story
        image.setImageResource(oneitem.imagename)//R.drawable.imageupload)

        val context = cardView.context
        cardView.setOnClickListener {
            var position: Int = adapterPosition
            Snackbar.make(it, "Click detected on item $position",
                    Snackbar.LENGTH_LONG).setAction("Action", null).show()

            val intent = Intent(context, PostActivity::class.java).apply {
                //putExtra("DataItem", oneitem.title)
                putExtra("DataItem", oneitem as Serializable)
                //get the object with: val object = intent.extras.get("DataItem") as DataItem
            }
            context.startActivity(intent)
        }
        if (oneitem.highlight) {
            cardView.setCardBackgroundColor(Color.parseColor("#E6E6E6"));
            cardView.maxCardElevation = 10.0F;
            //cardView.radius = 5.0F;
        }
        //header.setTextColor(Color.parseColor("#ffffff"))
        title.setTextColor(context.getColor(R.color.primaryDarkColor))
        //description.setTextColor(Color.parseColor("#ffa270"))
        story.setTextColor(context.getColor(R.color.secondaryDarkColor))
    }

}

class MainCardAdapter(val data: List<DataItem>) : RecyclerView.Adapter<MainCardViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainCardViewHolder {
        //TODO("Not yet implemented")
        //create an instance of LayoutInflater
        //The layout inflater knows how to create views from XML layouts. The context contains information on how to correctly inflate the view.
        // In an adapter for a recycler view, you always pass in the context of the parent view group, which is the RecyclerView.
        val layoutInflater = LayoutInflater.from(parent.context)

        //create the view by asking the layoutinflater to inflate it.
        //Pass in the XML layout for the view, and the parent view group for the view. The third, boolean, argument is attachToRoot.
        // This argument needs to be false, because RecyclerView adds this item to the view hierarchy for you when it's time.
        val view = layoutInflater
            .inflate(R.layout.card_item_view, parent, false) as CardView


        //val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item_view, parent, false)
        return MainCardViewHolder(view)
    }

    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return data.size
    }

    override fun onBindViewHolder(holder: MainCardViewHolder, position: Int) {
        //TODO("Not yet implemented")
        holder.bind(data[position])
    }

}