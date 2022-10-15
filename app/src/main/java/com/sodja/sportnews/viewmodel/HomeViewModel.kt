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
class HomeViewModel @Inject constructor(
    private val sportNewsRepository: SportNewsRepository
): ViewModel() {
    private val _sportNewsState = mutableStateOf<Resource<List<News>>>(Resource.Success(null))
    val sportNewsState: State<Resource<List<News>>> = _sportNewsState

    fun getSportNews() {
        viewModelScope.launch {
            sportNewsRepository.getAllNews().collect { response ->
                _sportNewsState.value = response
            }
        }
    }
}