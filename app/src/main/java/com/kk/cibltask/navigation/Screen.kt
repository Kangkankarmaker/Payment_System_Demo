package com.kk.cibltask.navigation

sealed class Screen(val route: String) {
    object Permission : Screen(route = "permission_screen")
    object DashBoard : Screen(route = "dashboard_screen")

    object Payment : Screen(route = "payment_screen?method={method}") {
        fun passPaymentMethod(method: String = ""): String {
            return "payment_screen?method=$method"
        }
    }
}

const val METHOD_ARGUMENT_KEY = "method"
var USERADDRESS = ""

object Graph {
    const val ROOT = "root_graph"
}
