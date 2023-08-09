package com.kk.cibltask.screens.payment

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun PaymentScreen(
    navController: NavHostController,
    paymentMethod: String?,
    paymentViewModel: PaymentViewModel = viewModel()
) {

    val paymentName = if (paymentMethod == "bk") "bKash" else "Nagad"

    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        PaymentSuccessDialog(
            showDialog = showDialog.value,
            onDismiss = {
                showDialog.value = false
            },
            paymentMethod=paymentMethod!!
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = paymentViewModel.number,
            onValueChange = {
                paymentViewModel.updateNumber(it)
            },
            placeholder = "$paymentName Number",
            icon = Icons.Default.Phone,
            keyboardType = KeyboardType.Phone
        )
        Spacer(modifier = Modifier.height(5.dp))

        TextField(
            value = paymentViewModel.name,
            onValueChange = {
                paymentViewModel.updateName(it)
            },
            placeholder = "$paymentName name",
            icon = Icons.Default.Person,
            keyboardType = KeyboardType.Text
        )
        Spacer(modifier = Modifier.height(5.dp))

        TextField(
            value = paymentViewModel.amount,
            onValueChange = {
                paymentViewModel.updateAmount(it)
            },
            placeholder = "Enter amount",
            icon = Icons.Default.CheckCircle,
            keyboardType = KeyboardType.Number
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextField(
            value = paymentViewModel.narration,
            onValueChange = {
                paymentViewModel.updateNarration(it)
            },
            placeholder = "Enter narration",
            icon = Icons.Default.List,
            keyboardType = KeyboardType.Text
        )
        Spacer(modifier = Modifier.height(8.dp))

        val context = LocalContext.current
        OutlinedButton(onClick = {
            if (!paymentViewModel.validateField()) {
                Toast.makeText(context, "Validation error", Toast.LENGTH_SHORT).show()
            }else{
                showDialog.value = true
            }
        }) {
            Text(text = " submit ")
        }
    }



}


@Composable
fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: ImageVector,
    keyboardType: KeyboardType
) {

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = "phone")
        },
        label = {
            Text(
                placeholder,
                style = TextStyle(
                    fontSize = 14.sp,
                )
            )
        },
        placeholder = {
            Text(
                modifier = Modifier.alpha(alpha = DefaultAlpha),
                text = "",
                color = Color.Gray
            )
        },
        textStyle = TextStyle(
            color = Color.Black
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = keyboardType,
        ),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            //backgroundColor = Color.Transparent,
            cursorColor = Color.DarkGray
        ),

        )
}