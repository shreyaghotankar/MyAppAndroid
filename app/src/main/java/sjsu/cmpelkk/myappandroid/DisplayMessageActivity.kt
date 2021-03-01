package sjsu.cmpelkk.myappandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DisplayMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        val message = intent.getStringExtra(EXTRA_MESSAGE)
        val messagetextView = findViewById<TextView>(R.id.messagetextView).apply {
            text = message
        }

        //for recyclerview
        val adapter = MyRecyclerAdapter()
        val recyclerView: RecyclerView = findViewById(R.id.myrecyclerview)
        recyclerView.adapter = adapter
    }
}

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)

class MyRecyclerAdapter: RecyclerView.Adapter<TextItemViewHolder>() {
    val data = List(30) {index ->  index*2}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView

        return TextItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }

}