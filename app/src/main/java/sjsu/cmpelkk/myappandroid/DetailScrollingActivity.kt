package sjsu.cmpelkk.myappandroid

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import sjsu.cmpelkk.myappandroid.myutil.imageUtil

class DetailScrollingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_scrolling)
        setSupportActionBar(findViewById(R.id.toolbar))

        //val dataitem: DataItem? = intent.extras?.get("DataItem") as? DataItem
        val dataTitle: Uri = intent.extras?.get("dataTitle") as Uri
        val dataDesc: Uri = intent.extras?.get("dataDesc") as Uri
        val dataImage: Uri = intent.extras?.get("dataImage") as Uri
        val dataUrl: Uri = intent.extras?.get("dataUrl") as Uri
        val dataAuthor: Uri = intent.extras?.get("dataAuthor") as Uri

        val toolbarlayout: CollapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        val collapsingtoolbarheaderimage = findViewById<ImageView>(R.id.collapsingtoolbarheaderimage)
        val collapsingtoolbarheadertext = findViewById<TextView>(R.id.collapsingtoolbarheadertext)
        val scrollText = findViewById<TextView>(R.id.nestedscrolltextview)

        if (dataDesc != null && dataTitle!=null) {

            toolbarlayout.title = dataTitle.toString()

            //toolbarlayout.setBackgroundResource(R.drawable.sjsu1)
            //collapsingtoolbarheaderimage.setImageURI(Uri.parse(dataitem.imagename))

            collapsingtoolbarheadertext.text = "Author: "+dataAuthor.toString()

            Glide.with(collapsingtoolbarheaderimage)
                    .load(dataImage)
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .into(collapsingtoolbarheaderimage)


            scrollText.text = dataDesc.toString()
            scrollText.append("\n\n"+ "Full Story here: "+dataUrl.toString())

            //findViewById<TextView>(R.id.nestedscrolltextview).text = dataitem.story
        }//toolbar.title = title

        //findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()*/
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            val contentBody = dataUrl.toString()
            shareIntent.putExtra(Intent.EXTRA_TEXT, contentBody)
            startActivity(Intent.createChooser(shareIntent, "Share News"))
        }

        //To enable the Up button for an activity that has a parent activity,
        // call the app bar's setDisplayHomeAsUpEnabled() method.
        setSupportActionBar(findViewById(R.id.toolbar)) //toolbar in activity_detail_scrolling.xml
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }
}