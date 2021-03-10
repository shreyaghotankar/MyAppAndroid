package sjsu.cmpelkk.myappandroid

import android.net.Uri
import java.io.Serializable

data class DataItem(
    val name: String,
    val title: String,
    val story: String,
    val highlight: Boolean = false,
    val rating: Int,
    val imagename: String //Int, //String?
): Serializable

//val yourDrawable  = ResourcesCompat.getDrawable(resources, R.drawable.sjsu1, null)
//Uri.parse("android.resource://" + packageName + "/" + R.drawable.sjsu1)

//public val carddefaultdata = listOf<DataItem>(
//    DataItem("name1", "testtitle1", "Test story, Test storyTest storyTest storyTest storyTest storyTest storyTest storyTest storyTest storyTest storyTest storyTest story", false, 4, R.drawable.imageupload),
//    DataItem("name2", "testtitle2", "Test story", true, 4, R.drawable.sjsu1),
//    DataItem("name3", "testtitle3", "Test story", false, 4, R.drawable.sjsu2),
//    DataItem("name4", "testtitle4", "Test story", false, 4, R.drawable.sjsu3)
//    )

public val carddefaultdata = listOf<DataItem>(
        DataItem("name1", "testtitle1", "Test story, Test storyTest storyTest storyTest storyTest storyTest storyTest storyTest storyTest storyTest storyTest storyTest story", false, 4, Uri.parse("android.resource://sjsu.cmpelkk.myappandroid" + "/" + R.drawable.sjsu1).toString()),
        DataItem("name2", "testtitle2", "Test story", true, 4, Uri.parse("android.resource://sjsu.cmpelkk.myappandroid" + "/" + R.drawable.sjsu1).toString()),//Uri is not serializable
        DataItem("name3", "testtitle3", "Test story", false, 4, Uri.parse("android.resource://sjsu.cmpelkk.myappandroid" + "/" + R.drawable.sjsu2).toString()),
        DataItem("name4", "testtitle4", "Test story", false, 4, Uri.parse("android.resource://sjsu.cmpelkk.myappandroid" + "/" + R.drawable.sjsu3).toString())
)
