package jp.making.felix.readrecorder

import android.util.Log
import okhttp3.*
import java.io.IOException
import java.lang.IndexOutOfBoundsException

class CallApi{

    /**
     *@param isbn番号
     *
     * APIを呼び本のデータが存在する場合には
     */
    fun call(isbn:String){
        val client = OkHttpClient()
        val url = "https://api.openbd.jp/v1/get?isbn=${isbn}"
        val req = Request.Builder().url(url).build()
        Log.i("BOOKDATA","HERE")
        Log.i("ISBN",isbn)
        client.newCall(req).enqueue(object : Callback{

            override fun onFailure(call: Call, e: IOException) {
                Log.i("BOOKDATA",e.toString())
            }
            override fun onResponse(call: Call, response: Response) {
                //呼び出しに成功した場合中身があることは確実なのでエルビス演算子を用いる。
                val body = response.body()!!.string()
                //TODO JSONにパースしていい感じに綺麗な処理にする
                val title = body.split("content\": \"")[1].split("\"")[0]
                //本の画像のURLが存在したらそれを登録する。　存在しない場合IndexOutOfBoundsが現在だと発生するのでその場合はから文字列で登録する
                //TODO 本が存在しない場合の画像を用意する
                // JSON処理実装後この処理も綺麗にする
                try {
                    val res: String = body.split("ResourceLink\": \"")[1].split("\"")[0]
                    registBook(res,title)
                }
                catch (e:IndexOutOfBoundsException){
                    registBook("",title)
                }


            }
        })
    }
    /**
     * @param res:リソースURL　title:本のタイトル
     * 本をDBに登録する関数 RealmControllerのスコープをできる限り狭くしたかった
     * */
    private fun registBook(res:String,title:String){
        Log.i("REGIST","HERE")
        RealmController.createData(title,res)
    }
}