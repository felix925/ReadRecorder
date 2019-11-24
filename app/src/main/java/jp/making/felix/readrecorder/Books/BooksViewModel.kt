package jp.making.felix.readrecorder.Books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.making.felix.readrecorder.Event
import jp.making.felix.readrecorder.R
import jp.making.felix.readrecorder.data.local.Books
import jp.making.felix.readrecorder.data.local.BooksRepository
import kotlinx.coroutines.launch
import java.util.ArrayList

class BooksViewModel(
    private val booksRepository: BooksRepository
):ViewModel(){
    private val _items = MutableLiveData<List<Books>>().apply { value = emptyList() }
    val items: LiveData<List<Books>> = _items

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _currentFilteringLabel = MutableLiveData<Int>()
    val currentFilteringLabel: LiveData<Int> = _currentFilteringLabel

    private val _noTasksLabel = MutableLiveData<Int>()
    val noTasksLabel: LiveData<Int> = _noTasksLabel

    private val _noTaskIconRes = MutableLiveData<Int>()
    val noTaskIconRes: LiveData<Int> = _noTaskIconRes

    private val _tasksAddViewVisible = MutableLiveData<Boolean>()
    val tasksAddViewVisible: LiveData<Boolean> = _tasksAddViewVisible

    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText

    private var _currentFiltering = BooksFilterType.ALL_BOOKS

    // Not used at the moment
    private val isDataLoadingError = MutableLiveData<Boolean>()

    private val _openTaskEvent = MutableLiveData<Event<String>>()
    val openTaskEvent: LiveData<Event<String>> = _openTaskEvent

    private val _newTaskEvent = MutableLiveData<Event<Unit>>()
    val newTaskEvent: LiveData<Event<Unit>> = _newTaskEvent

    init {
        //TODO フィルタリングを実装する
        //setFiltering(BooksFilterType.ALL_BOOKS)
        //loadBooks(true)
    }
}