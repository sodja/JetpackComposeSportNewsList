package com.sodja.sportnews.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.sodja.sportnews.commons.Resource
import com.sodja.sportnews.domain.model.News
import com.sodja.sportnews.domain.repository.SportNewsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val sportNewsRepository: SportNewsRepository
): ViewModel() {
    private val _newState = mutableStateOf<Resource<News>>(Resource.Success(null))
    val newState: State<Resource<News>> = _newState

    fun getDetailNews(id: Int) {
        viewModelScope.launch {
            sportNewsRepository.getDetailNews(id).collect { response ->
                _newState.value = response
            }
        }
    }
}