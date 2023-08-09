package com.kk.cibltask.screens.payment


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kk.cibltask.R
import com.kk.cibltask.navigation.USERADDRESS
import com.kk.cibltask.pdf.generatePDF
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentSuccessDialog(
    showDialog: Boolean,
    paymentMethod: String,
    onDismiss: () -> Unit,
    paymentViewModel: PaymentViewModel = viewModel()
) {
    val paymentName = if (paymentMethod == "bk") "bKash" else "Nagad"

    if (showDialog) {
        Dialog(onDismissRequest = { onDismiss() }) {

            Card(
                shape = RoundedCornerShape(13.dp)
            ) {

                Column(
                    modifier = Modifier
                        .size(600.dp)
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if (paymentMethod == "bk") {
                                Image(
                                    modifier = Modifier
                                        .size(80.dp),
                                    painter = painterResource(id = R.drawable.bkash_money_send_icon),
                                    contentDescription = ""
                                )
                            }
                            if (paymentMethod == "ng") {
                                Image(
                                    modifier = Modifier
                                        .size(60.dp),
                                    painter = painterResource(id = R.drawable.ic_nagad),
                                    contentDescription = ""
                                )
                            }
                            Text(
                                text = "$paymentName fund transfer",
                                fontSize = 10.sp
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.End
                        ) {

                            IconButton(
                                onClick = { onDismiss() }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close"
                                )
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    modifier = Modifier
                                        .size(40.dp),
                                    painter = painterResource(id = R.drawable.cbbl_logo),
                                    contentDescription = ""
                                )
                                Text(
                                    text = "Community Bank",
                                    color = colorResource(id = R.color.logo_color),
                                    fontSize = 16.sp
                                )

                            }
                            Text(
                                text =USERADDRESS,
                                fontSize = 10.sp
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(text = "COMMUNITY CASHT")
                        Text(text = "TRANSACTION RECEIPT")
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.LightGray)
                                .padding(3.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Source Account/Card")
                            Text(text = "0018228472")
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(3.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Amount")
                            Text(text = "BTD ${paymentViewModel.amount}")
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(3.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Transaction Date time")
                            Spacer(modifier = Modifier.width(5.dp))
                            val timeDate = getCurrentDate()
                            Text(text = timeDate)
                        }



                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(3.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Narration")
                            Text(text = paymentViewModel.narration)
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.LightGray)
                                .padding(3.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Transaction Info")
                            Text(text = "")
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(3.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "$paymentName number")
                            Text(text = paymentViewModel.number)
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(3.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Reference No")
                            Text(text = "TXN1685")
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(3.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Total")
                            Text(text = paymentViewModel.amount)
                        }
                    }

                    Image(
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.End),
                        painter = painterResource(id = R.drawable.successseal),
                        contentDescription = "successseal"
                    )
                }

                val ctx = LocalContext.current

                FilledTonalButton(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    onClick = {
                        generatePDF(ctx,paymentMethod,paymentViewModel)
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "DOWNLOAD RECEIPT",
                            color = Color.Gray
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_vertical_align_bottom_24),
                            contentDescription = "download",
                            tint = Color.Gray
                        )
                    }

                }

            }
        }

    }
}

fun getCurrentDate(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    return sdf.format(Date())
}