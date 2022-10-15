package com.sodja.sportnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import com.sodja.sportnews.commons.Constants.DETAIL_ARG_NEWS_ID
import com.sodja.sportnews.navigation.Routes
import com.sodja.sportnews.ui.fragment.home.HomeFragment
import com.sodja.sportnews.ui.fragment.detail.DetailFragment
import com.sodja.sportnews.ui.theme.SportNewsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SportNewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SportNewsAppScreen()
                }
            }
        }
    }
}

@Composable
fun SportNewsAppScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route,
    ) {
        composable(route = Routes.Home.route) {
            HomeFragment(
                onClickToDetailScreen = { newsId ->
                    navController.navigate(
                        Routes.Detail.createRoute(newsId)
                    )
                }
            )
        }
        composable(
            route = Routes.Detail.route,
            arguments = listOf(
                navArgument(DETAIL_ARG_NEWS_ID){
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val newsId = backStackEntry.arguments?.getInt(DETAIL_ARG_NEWS_ID)
            requireNotNull(newsId) { "newsId parameter wasn't found. Please make sure it's set!" }
            DetailFragment(id = newsId,
                    onClickToBack = { _ ->
                navController.popBackStack()
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SportNewsTheme {
        SportNewsAppScreen()
    }
}