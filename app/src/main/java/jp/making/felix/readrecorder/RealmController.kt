package jp.making.felix.readrecorder

import io.realm.Realm
import io.realm.RealmResults
import java.text.SimpleDateFormat
import java.util.*

class RealmController{
    fun createData(name: String,imageUrl: String,mRealm:Realm){
        mRealm.executeTransaction{
            val bookData = mRealm.createObject(Books::class.java,UUID.randomUUID().toString())
            bookData.name = name
            bookData.imageUrl = imageUrl
            bookData.lastLog = getDate()
            mRealm.copyToRealm(bookData)
        }
    }

    fun readData(mRealm: Realm):RealmResults<Books>{
        return mRealm.where(Books::class.java).findAll()
    }

    fun findData(id:String,mRealm: Realm):Books?{
        return mRealm.where(Books::class.java).equalTo("id",id).findFirst()
    }

    fun updateData(id: String,page:Int,mRealm: Realm){
        mRealm.executeTransaction{
            val book = mRealm.where(Books::class.java).equalTo("id",id).findFirst()
            book?.apply {
                book.pages.add(Page(page))
            }
        }
    }

    private fun getDate():String{
        val date = Date()
        val dataFormat = SimpleDateFormat("yyyy/MM/dd",Locale.getDefault())
        return dataFormat.format(date)
    }
}