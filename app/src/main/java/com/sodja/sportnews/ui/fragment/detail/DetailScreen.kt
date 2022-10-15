package com.sodja.sportnews.ui.fragment.detail

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.sodja.sportnews.ui.component.IconHeader
import com.sodja.sportnews.ui.component.ImageCarousel
import com.sodja.sportnews.domain.model.News
import com.sodja.sportnews.ui.component.VideoPlayer
import com.sodja.sportnews.ui.theme.AuthorColor
import com.sodja.sportnews.ui.theme.Purple200
import com.sodja.sportnews.ui.theme.SportNewsTheme
import java.text.SimpleDateFormat

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    news: News? = null,
    onClickToBack: (Int) -> Unit = {}
) {
    if(news == null) return
    val scrollState = rememberScrollState()
    val title = news.title ?: ""
    val description = news.teaser ?: ""
    val author = news.author ?: ""
    val listImageCarousel = mutableListOf<String>()
    news.image?.let {
        listImageCarousel.add(it)
    }
    news.image?.let {
        listImageCarousel.add(it)
    }
    if( news.url == null){
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color.White)
                .verticalScroll(scrollState)
        ) {

            BoxWithConstraints(
                Modifier
                    .height(200.dp)
                    .padding(0.dp)) {

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(185.dp)) {
                    IconHeader(
                        modifier = Modifier
                            .padding(16.dp)
                            .zIndex(2f),
                        name = title,
                        onClickToBack = onClickToBack
                    )
                    ImageCarousel(
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth(),
                        listImage = listImageCarousel
                    )
                }
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.BottomStart)
                        .padding(start = 25.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(color = Purple200)
                        .zIndex(2f),
                ) {
                    news.sport?.let { Text(it.name, modifier = Modifier
                        .padding(3.dp)
                        .align(alignment = Alignment.Center)) }
                }
            }
            Column(
                modifier = Modifier
                .padding(
                    top = 25.dp,
                    start = 25.dp,
                    end = 25.dp
                )) {
                Text(
                    text = title+"\n",
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.Black
                )
                Row {
                    Text(
                        text = "By ",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                    Text(
                    text = author,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AuthorColor
                    )
                }
                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
                val dateString = simpleDateFormat.format(news.date ?: "")
                Text(
                    text = dateString,
                    fontSize = 10.sp,
                    fontFamily= FontFamily.Default,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = description,
                    fontSize = 12.sp,
                    fontFamily= FontFamily.Serif,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }


        }
    } else{
        VideoPlayer(Uri.parse(news.url))
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    SportNewsTheme {
        DetailScreen()
    }
}