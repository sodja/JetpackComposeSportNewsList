package com.sodja.sportnews.navigation

import com.sodja.sportnews.commons.Constants.DETAIL_ARG_NEWS_ID
import com.sodja.sportnews.commons.Constants.DETAIL_SCREEN
import com.sodja.sportnews.commons.Constants.LIST_SCREEN

sealed class Routes(val route: String) {
    object Home : Routes(LIST_SCREEN)
    object Detail : Routes("$DETAIL_SCREEN/{$DETAIL_ARG_NEWS_ID}") {
        fun createRoute(newId: Int) = "$DETAIL_SCREEN/$newId"
    }
}