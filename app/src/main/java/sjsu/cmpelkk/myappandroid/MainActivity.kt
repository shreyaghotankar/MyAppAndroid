package sjsu.cmpelkk.myappandroid

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import sjsu.cmpelkk.myappandroid.myutil.SwipeToDeleteCallback
import java.io.Serializable

const val POST_REQUEST_CODE = 32
class MainActivity : AppCompatActivity() {
    var datalist: MutableList<DataItem> = mutableListOf()
    lateinit var recyclerCard: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerCard = findViewById(R.id.cardrecyclerview)
        datalist = carddefaultdata.toMutableList()
        recyclerCard.adapter = MainCardAdapter(datalist) //(carddefaultdata)

        val swipeHandler = object: SwipeToDeleteCallback(this){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerCard.adapter as MainCardAdapter
                adapter.removeAt(viewHolder.adapterPosition)

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerCard)

        val fab: View = findViewById(R.id.floatingActionButton)
        fab.setOnClickListener { view ->
            val intent = Intent(this, PostActivity::class.java)
            //startActivity(intent)
            startActivityForResult(intent, POST_REQUEST_CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == POST_REQUEST_CODE) {
            Toast.makeText(this, data?.dataString, Toast.LENGTH_LONG).show()//title data
            val dataitem: DataItem? = data?.extras?.get("NewDataItem") as? DataItem
            if (dataitem != null) {
                datalist.add(datalist.lastIndex+1, dataitem)
                val myadapter = recyclerCard.adapter
                if (myadapter != null) {
                    //myadapter.notifyDataSetChanged()
                    myadapter.notifyItemInserted(datalist.lastIndex+1)
                }

            }
        }
    }
}

class MainCardViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView) {
    val title: TextView = cardView.findViewById(R.id.carditemtitletextView)
    val story: TextView = cardView.findViewById(R.id.carditemdetailtext)
    val image: ImageView = cardView.findViewById(R.id.cardimageView)

    fun bind(oneitem: DataItem) {
        title.text = oneitem.title
        story.text = oneitem.story
        //image.setImageResource(oneitem.imagename)//Use a resource id to set the content of the ImageView., R.drawable.imageupload)
        //image.setImageURI(oneitem.imagename)
        image.setImageURI(Uri.parse(oneitem.imagename))
        val context = cardView.context
        cardView.setOnClickListener {
            var position: Int = adapterPosition
            Snackbar.make(it, "Click detected on item $position",
                    Snackbar.LENGTH_LONG).setAction("Action", null).show()

            val intent = Intent(context, DetailScrollingActivity::class.java).apply {
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

class MainCardAdapter(var data: MutableList<DataItem>) : RecyclerView.Adapter<MainCardViewHolder>()
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

    fun removeAt(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

}