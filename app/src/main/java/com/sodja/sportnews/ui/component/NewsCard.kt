package com.sodja.sportnews.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.sodja.sportnews.R
import com.sodja.sportnews.ui.theme.Purple200
import com.sodja.sportnews.ui.theme.SportNewsTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    name: String = "",
    imageUrl: String = "",
    videoUrl: String = "",
    releaseDate: String = "",
    sport: String = "",
    onClickNew: () -> Unit = {},
) {
    val imagePainter = rememberAsyncImagePainter(
        model = imageUrl,
        error = painterResource(id = R.drawable.ic_launcher_foreground),
    )

    val videoPlayPainter = painterResource(id = R.drawable.play)

    Card(
        onClick = onClickNew,
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        backgroundColor = Color(0xFFF8F8F8),
        elevation = 4.dp,
        shape = RoundedCornerShape(5.dp),
    ) {

        Column(
            horizontalAlignment = Alignment.Start,
        ) {
            BoxWithConstraints(
                Modifier
                    .height(142.dp)
                    .padding(0.dp)) {

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)) {
                    Image(
                        painter = imagePainter,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentScale = ContentScale.Crop
                    )
                }
                if(videoUrl.isNotEmpty()){
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.Center)
                            .padding(start = 16.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .zIndex(2f),
                    ) {
                        Image(
                            painter = videoPlayPainter,
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.BottomStart)
                        .padding(start = 16.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(color = Purple200)
                        .zIndex(2f),
                ) {
                    Text(sport.uppercase(),fontSize = 12.sp, modifier = Modifier.padding(start = 8.dp,
                        end = 8.dp, top = 5.dp, bottom = 5.dp).align(alignment = Alignment.Center))
                }
            }

            Box(contentAlignment = Alignment.BottomStart) {
                Column(
                    modifier = Modifier.padding(start = 10.dp, top = 8.dp, end = 10.dp, bottom = 10.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = name+"\n",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W700,
                        color = Color.Black
                    )
                    Text(
                        text = releaseDate,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    SportNewsTheme {
        NewsCard()
    }
}