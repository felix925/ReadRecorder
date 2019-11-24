package jp.making.felix.readrecorder.data.local

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import jp.making.felix.readrecorder.Page
import java.io.Serializable

open class Books:RealmObject(),Serializable{
    @PrimaryKey
    var id: String = ""
    @Required
    var name: String = ""
    var imageUrl: String = ""
    var lastLog: String = ""
    var pages: RealmList<Page> = RealmList(
        Page(0)
    )
}