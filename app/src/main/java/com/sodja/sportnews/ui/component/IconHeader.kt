package com.sodja.sportnews.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sodja.sportnews.R
import com.sodja.sportnews.ui.theme.SportNewsTheme

@Composable
fun IconHeader(
    modifier: Modifier = Modifier,
    name: String = "",
    onClickToBack: (Int) -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement  =  Arrangement.SpaceBetween
    ) {
        val backIcon = painterResource(id = R.drawable.back)
        val shareIcon = painterResource(id = R.drawable.share)

        Image(
            painter = backIcon,
            contentDescription = name,
            modifier = Modifier
                .size(30.dp)
                .clickable(
                    enabled = true,
                    onClick = {
                        onClickToBack.invoke(0)
                    }
                )
                .background(color = Color.Black)
                .padding(5.dp)
            ,
        )
        Image(
            painter = shareIcon,
            contentDescription = name,
            modifier = Modifier
                .size(30.dp)
                .background(color = Color.Black)
                .padding(5.dp)

            ,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductHeaderPreview() {
    SportNewsTheme {
        IconHeader()
    }
}