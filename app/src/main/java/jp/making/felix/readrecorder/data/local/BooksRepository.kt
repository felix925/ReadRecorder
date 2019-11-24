package jp.making.felix.readrecorder.data.local

import io.realm.RealmResults

interface BooksRepository{
    suspend fun getBooks(): RealmResults<Books>

    suspend fun getBook(bookId:String):Books

    suspend fun deleteAllBooks()

    suspend fun deleteBooks(bookId: String)

}