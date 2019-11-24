package jp.making.felix.readrecorder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jp.making.felix.readrecorder.Books.BooksViewModel
import jp.making.felix.readrecorder.data.local.BooksRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val booksRepository: BooksRepository
):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass){
            when{
                isAssignableFrom(BooksViewModel::class.java) ->
                    BooksViewModel(booksRepository)
                else ->
                    throw IllegalAccessException("Unknown ViewModel class: ${modelClass}")
            }
        } as T
}