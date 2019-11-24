package jp.making.felix.readrecorder

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.squareup.picasso.Picasso
import jp.making.felix.readrecorder.data.local.Books
import kotlinx.android.synthetic.main.book_item.view.*

class UserAdapter(val context: Context, val users: List<Books>): BaseAdapter() {

    val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return users.count()
    }

    override fun getItem(position: Int): Books {
        return users[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = layoutInflater.inflate(R.layout.book_item, parent, false)
        if(users[position].imageUrl.isNotEmpty()) {
            Picasso.get().load(users[position].imageUrl).into(view.thumbnail)
        }
        view.name.text = users[position].name
        view.comment.text = users[position].lastLog
        return view
    }
}