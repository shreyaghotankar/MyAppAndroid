package sjsu.cmpelkk.myappandroid

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
import sjsu.cmpelkk.myappandroid.myutil.imageUtil

class DetailScrollingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_scrolling)
        setSupportActionBar(findViewById(R.id.toolbar))

        val dataitem: DataItem? = intent.extras?.get("DataItem") as? DataItem
        val toolbarlayout: CollapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        val collapsingtoolbarheaderimage = findViewById<ImageView>(R.id.collapsingtoolbarheaderimage)
        val collapsingtoolbarheadertext = findViewById<TextView>(R.id.collapsingtoolbarheadertext)
        if (dataitem != null) {
            toolbarlayout.title = dataitem.title
            //toolbarlayout.setBackgroundResource(R.drawable.sjsu1)
            collapsingtoolbarheaderimage.setImageURI(Uri.parse(dataitem.imagename))
            collapsingtoolbarheadertext.text = "Author: "+dataitem.name

            //toolbarlayout.setBackgroundResource(dataitem.imagename)//Drawable object or 0 to remove the background.
//            val inputStream = contentResolver.openInputStream(dataitem.imagename)
//            val b = BitmapFactory.decodeStream(inputStream)//Creates Bitmap objects
//            val bMapScaled = imageUtil.BitmapscaleToFitHeight(b, R.dimen.app_bar_height)
//            toolbarlayout.setBackgroundResource(BitmapDrawable(resources, bMapScaled))

            findViewById<TextView>(R.id.nestedscrolltextview).text = dataitem.story
        }//toolbar.title = title

        //findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        //To enable the Up button for an activity that has a parent activity,
        // call the app bar's setDisplayHomeAsUpEnabled() method.
        setSupportActionBar(findViewById(R.id.toolbar)) //toolbar in activity_detail_scrolling.xml
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }
}