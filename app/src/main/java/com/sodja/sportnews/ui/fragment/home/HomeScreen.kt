package com.sodja.sportnews.ui.fragment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sodja.sportnews.R
import com.sodja.sportnews.domain.model.News
import com.sodja.sportnews.ui.component.NewsCard
import com.sodja.sportnews.ui.component.VideoPlayer
import com.sodja.sportnews.ui.theme.Purple500
import java.text.SimpleDateFormat

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    newsList: List<News>?,
    onClickToDetailScreen: (Int) -> Unit = {}
) {
    Column {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Purple500), contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(id = R.string.home_title).uppercase(),
                fontSize = 16.sp,
                fontWeight = FontWeight.W700,
                color = Color.White
            )
        }
        ListScreen(
            modifier = modifier,
            newsList = newsList,
            onClickToDetailScreen = onClickToDetailScreen
        )
    }

}

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    newsList: List<News>?,
    onClickToDetailScreen: (Int) -> Unit = {},
) {
    if (newsList == null) return
    LazyColumn {
        items(newsList.size) { index ->
            newsList[index].let { new ->
                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
                val dateString = simpleDateFormat.format(new.date ?: "")
                val id = new.id
                val name = new.title ?: ""
                val imageUrl = new.image ?: new.thumb?:""
                val releaseDate = dateString ?: ""
                val sport = new.sport?.name ?: ""
                val videoUrl = new.url?: ""
                NewsCard(
                    modifier = modifier
                        .padding(8.dp),
                    name = name,
                    imageUrl = imageUrl,
                    releaseDate = releaseDate,
                    sport = sport,
                    videoUrl = videoUrl,
                    onClickNew = {
                        id?.let {
                            onClickToDetailScreen.invoke(it)
                        }
                    }
                )
            }
        }
    }
}
