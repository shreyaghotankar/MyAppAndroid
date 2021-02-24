package sjsu.cmpelkk.myappandroid

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

const val EXTRA_MESSAGE = "sjsu.cmpelkk.MyAppAndroid.MESSAGE"

class PostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
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
        if (resultCode == Activity.RESULT_OK)
        {
            Toast.makeText(this,"Activity result received", Toast.LENGTH_LONG).show()
        }
    }
}