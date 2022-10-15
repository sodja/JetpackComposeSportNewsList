package com.sodja.sportnews.ui.fragment.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sodja.sportnews.ui.component.LoadingCircular
import com.sodja.sportnews.R
import com.sodja.sportnews.commons.Resource
import com.sodja.sportnews.ui.component.ErrorButton
import com.sodja.sportnews.ui.fragment.HomeScreen
import com.sodja.sportnews.viewmodel.HomeViewModel

@Composable
fun HomeFragment(modifier: Modifier = Modifier,
               homeViewModel: HomeViewModel = hiltViewModel(),
               onClickToDetailScreen: (Int) -> Unit = {}) {
    fun launch() {
        homeViewModel.getSportNews()
    }

    launch()
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when(val newsResponse = homeViewModel.sportNewsState.value){
            is Resource.Loading -> {
                LoadingCircular(
                    modifier = Modifier.fillMaxWidth()
                )
            }
            is Resource.Success -> {
                HomeScreen(
                    modifier = Modifier
                        .padding(
                            horizontal = 5.dp
                        ),
                    newsList = newsResponse.data,
                    onClickToDetailScreen = onClickToDetailScreen,
                )
            }
            is Resource.Failure -> {
                ErrorButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.error_message),
                    onClick = {
                        launch()
                    }
                )
            }
        }
    }
}

