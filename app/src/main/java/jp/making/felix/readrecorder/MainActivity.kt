package jp.making.felix.readrecorder

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.book_item.*
import kotlinx.android.synthetic.main.book_item.view.*
import java.io.Serializable


data class Books(
    val id:Int,
    val name:String,
    val imageUrl:String,
    val lastLog:String,
    val pages:Array<Int>
):Serializable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val users = mutableListOf<Books>()

        val imageURL = "./res/brawable/kotlin.jpg"

        users.add(Books(1, "ユーザー1", imageURL, "最終更新:" + "2019/11/05", arrayOf(0,50,100,150,220)))
        users.add(Books(2, "ユーザー2", imageURL, "最終更新:" + "2019/11/05", arrayOf(0)))
        users.add(Books(3, "ユーザー3", imageURL, "最終更新:" + "2019/11/05", arrayOf(0)))
        users.add(Books(4, "ユーザー4", imageURL, "最終更新:" + "2019/11/05", arrayOf(0)))

        listView.adapter = UserAdapter(this, users)
        listView.setOnItemClickListener { parent, view, position, id ->
            val books = users.get(id.toInt())
//            Toast.makeText(this, "Clicked: ${name}", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,BookDataView::class.java)
            intent.putExtra("book",books)
            startActivity(intent)
        }
        fab.setOnClickListener { view ->
            //            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
            val mem = users.size + 1
            users.add(Books(mem, "ユーザー${mem}", imageURL, "最終更新:" + "2019/11/05", arrayOf(0)))
            listView.adapter = UserAdapter(this, users)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}


