package jp.making.felix.readrecorder

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        Realm.init(this)
        val rcon = RealmController()
        val rdata = rcon.readData()
        listView.adapter = UserAdapter(this, rdata)
        listView.setOnItemClickListener { parent, view, position, id ->
            //本の情報を受け渡すためにstringのリストで渡す
            val intent = Intent(this, BookDataView::class.java)
            rdata[position]?.apply {
                intent.putExtra("bookId",this.id)
            }
            startActivity(intent)
        }
        fab.setOnClickListener { view ->
            val intent = Intent(this, BookRegist::class.java)
            startActivity(intent)
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