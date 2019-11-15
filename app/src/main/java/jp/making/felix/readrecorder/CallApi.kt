package jp.making.felix.readrecorder

import android.util.Log
import okhttp3.*
import java.io.IOException

class CallApi{

    fun call(isbn:String){
        val client = OkHttpClient()
        val url = "https://api.openbd.jp/v1/get?isbn=4-7973-2973-4"
        val req = Request.Builder().url(url).build()
        Log.i("BOOKDATA","HERE")
        Log.i("ISBN",isbn)
        client.newCall(req).enqueue(object : Callback{

            override fun onFailure(call: Call, e: IOException) {
                Log.i("BOOKDATA",e.toString())
            }
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()!!.string()
                //TODO JSONにパースしていい感じに綺麗な処理にする
                val res = body.split("ResourceLink\": \"")[1].split("\"")[0]
                val title = body.split("content\": \"")[1].split("\"")[0]
                registBook(res,title)
            }
        })
    }
    private fun registBook(res:String,title:String){
        Log.i("REGIST","HERE")
        val rcon = RealmController()
        rcon.createData(title,res)
    }
}