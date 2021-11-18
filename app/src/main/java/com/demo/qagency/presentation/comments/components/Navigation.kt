package com.demo.qagency.presentation.comments.components

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.demo.qagency.presentation.comments.CommentsListScreen
import com.demo.qagency.util.Screen

@Composable
fun Navigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
) {
    NavHost(
            navController = navController,
            startDestination = Screen.CommentListScreen.route,
            modifier = Modifier.fillMaxSize()
    ) {
        composable(
                route = Screen.CommentListScreen.route
        ) {
            CommentsListScreen(
                    scaffoldState = scaffoldState
            )
        }
    }
}