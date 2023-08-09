package com.kk.cibltask.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kk.cibltask.R
import com.kk.cibltask.navigation.Screen

@Composable
fun DashBoardScreen(
    navController: NavHostController,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(165.dp)
                .clickable {
                    navController.navigate(Screen.Payment.passPaymentMethod("bk"))
                },
            painter = painterResource(id = R.drawable.bkash_money_send_icon),
            contentDescription = "bkash"
        )

        Spacer(modifier = Modifier.height(15.dp))

        Image(
            modifier = Modifier
                .size(165.dp)
                .clickable {
                    navController.navigate(Screen.Payment.passPaymentMethod("ng"))
                },
            painter = painterResource(id = R.drawable.ic_nagad),
            contentDescription = "nagad"
        )
    }

}