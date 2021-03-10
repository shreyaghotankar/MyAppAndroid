package sjsu.cmpelkk.myappandroid.myutil

import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


object imageUtil {
    // Scale and maintain aspect ratio given a desired width
    // BitmapScaler.scaleToFitWidth(bitmap, 100);
    fun BitmapscaleToFitWidth(b: Bitmap, width: Int): Bitmap {
        val factor = width / b.width.toFloat()
        return Bitmap.createScaledBitmap(b, width, (b.height * factor).toInt(), true)
    }
    // Scale and maintain aspect ratio given a desired height
    // BitmapScaler.scaleToFitHeight(bitmap, 100);
    fun BitmapscaleToFitHeight(b: Bitmap, height: Int): Bitmap {
        val factor = height / b.height.toFloat()
        return Bitmap.createScaledBitmap(b, (b.width * factor).toInt(), height, true)
    }

    fun Bitmapscale(b: Bitmap, scale: Double): Bitmap {
        //b.density = Bitmap.DENSITY_NONE
        val width: Int = (b.width * scale).toInt()
        val height: Int = (b.height * scale).toInt()
        return Bitmap.createScaledBitmap(b, width, height, true)
    }

    fun getURIfromresourceID(packageName: String, resourceString: String): Uri {
        //val uri = Uri.parse("android.resource://" + packageName + "/" + R.drawable.sjsu1)
        val uri = Uri.parse("android.resource://$packageName/$resourceString")
        //myImage.setImageURI(uri)
        return uri

    }

    fun getDrawablefromURI(contentResolver: ContentResolver, resources: Resources, uri: Uri): Drawable{
        val inputStream = contentResolver.openInputStream(uri)
        //val yourDrawable = Drawable.createFromStream(inputStream, uri.toString())
        val b = BitmapFactory.decodeStream(inputStream)//Creates Bitmap objects
        // Resize the bitmap to 150x100 (width x height)
        val bMapScaled = Bitmap.createScaledBitmap(b, 150, 100, true)
        //bMapScaled.density = Bitmap.DENSITY_NONE
        //myImage.setImageDrawable(yourDrawable)
        return BitmapDrawable(resources, bMapScaled)
    }

    fun getDrawablefromBitmap(resources: Resources, bitmap: Bitmap): Drawable {
        //imageView.setImageDrawable(d)
        return BitmapDrawable(resources, bitmap)
    }

    fun getBitmapfromFile(filename: String){
        val bMap = BitmapFactory.decodeFile(filename) //"/sdcard/test2.png"
        //image.setImageBitmap(bMap)
    }

    fun bitmapToFile(bitmap: Bitmap, fileNameToSave: String): File? { // File name like "image.png"
        //create a file to write bitmap data
        var file: File? = null
        return try {
            file = File(Environment.getExternalStorageDirectory().toString() + File.separator + fileNameToSave)
            file.createNewFile()

            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            val bitmapdata = bos.toByteArray()

            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitmapdata)
            fos.flush()
            fos.close()
            file
        } catch (e: Exception) {
            e.printStackTrace()
            file // it will return null
        }
    }

    fun writeToTempImageAndGetPathUri(inContext: Context, inImage: Bitmap, title: String): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, title, null)
        return Uri.parse(path)
    }
}