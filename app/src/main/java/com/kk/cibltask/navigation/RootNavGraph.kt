package com.kk.cibltask.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kk.cibltask.screens.DashBoardScreen
import com.kk.cibltask.screens.payment.PaymentScreen

@Composable
fun RootNavigationGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Screen.DashBoard.route
    ) {

        composable(route = Screen.DashBoard.route) {
            DashBoardScreen(navController = navController)
        }


        composable(
            route = Screen.Payment.route,
            arguments = listOf(
                navArgument(METHOD_ARGUMENT_KEY) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            PaymentScreen(
                navController = navController,
                paymentMethod = it.arguments?.getString(METHOD_ARGUMENT_KEY)
            )
        }

    }
}


