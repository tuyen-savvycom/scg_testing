package com.sav.news.ui.news_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.sav.news.base.BaseViewModel
import com.sav.news.data.usecase.NewsUserCase
import com.sav.news.models.News
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val newsUserCase: NewsUserCase
) : BaseViewModel() {

    private val _newList = MutableLiveData<PagingData<News>>()
    val newsList = _newList

    fun getLstNews(keyWord: CharSequence) {
        viewModelScope.launch {
            newsUserCase(this, "us", keyWord.toString()).collect {
                _newList.value = it
            }
        }
    }

}