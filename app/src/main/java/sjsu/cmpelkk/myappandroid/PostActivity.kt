package sjsu.cmpelkk.myappandroid

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

const val EXTRA_MESSAGE = "sjsu.cmpelkk.MyAppAndroid.MESSAGE"
const val IMAGE_REQUEST_CODE = 33

class PostActivity : AppCompatActivity() {
    lateinit var myImage: ImageView
    lateinit var textmultiline: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        //val myImage: ImageView = findViewById(R.id.uploadimageView)
        myImage = findViewById(R.id.uploadimageView)
        myImage.setOnClickListener{changeImage()}

        textmultiline = findViewById(R.id.editTextMultiLine)
        textmultiline.setOnTouchListener { view, event ->
            view.parent.requestDisallowInterceptTouchEvent(true)
            if ((event.action and MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
                view.parent.requestDisallowInterceptTouchEvent(false)
            }
            return@setOnTouchListener false
        }

    }

    private fun changeImage() {
        //val myImage: ImageView = findViewById(R.id.uploadimageView)
        //myImage.setImageResource(R.drawable.ic_baseline_star_rate_24)

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent,IMAGE_REQUEST_CODE)
    }

    fun sendMessage(view: View) {
        Toast.makeText(this,"Button clicked", Toast.LENGTH_LONG).show()

        val editText = findViewById<EditText>(R.id.nameTextfield)
        val message = editText.text.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode != IMAGE_REQUEST_CODE)
        {
            Toast.makeText(this,"Activity result received", Toast.LENGTH_LONG).show()
        }

        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_REQUEST_CODE) {
            myImage.setImageURI(data?.data)
        }
    }
}