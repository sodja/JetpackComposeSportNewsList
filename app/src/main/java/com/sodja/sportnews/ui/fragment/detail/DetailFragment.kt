package com.sodja.sportnews.ui.fragment.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.sodja.sportnews.R
import com.sodja.sportnews.commons.Resource
import com.sodja.sportnews.ui.component.ErrorButton
import com.sodja.sportnews.ui.component.LoadingCircular
import com.sodja.sportnews.ui.theme.SportNewsTheme
import com.sodja.sportnews.viewmodel.DetailViewModel

@Composable
fun DetailFragment(
    modifier: Modifier = Modifier,
    detailViewModel: DetailViewModel = hiltViewModel(),
    id: Int = -1,
    onClickToBack: (Int) -> Unit = {}
) {

    fun launch() {
        detailViewModel.getDetailNews(id)
    }

    launch()
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when(val newResponse = detailViewModel.newState.value){
            is Resource.Loading -> {
                LoadingCircular(
                    modifier = Modifier.fillMaxWidth()
                )
            }
            is Resource.Success -> {
                DetailScreen(
                    news = newResponse.data,
                    onClickToBack = onClickToBack
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

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailFragmentPreview() {
    SportNewsTheme {
        DetailFragment()
    }
}