package com.kk.cibltask.pdf

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.os.Environment
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.kk.cibltask.R
import com.kk.cibltask.navigation.USERADDRESS
import com.kk.cibltask.screens.payment.PaymentViewModel
import com.kk.cibltask.screens.payment.getCurrentDate
import java.io.File
import java.io.FileOutputStream


@RequiresApi(Build.VERSION_CODES.KITKAT)
fun generatePDF(
    context: Context,
    paymentMethod: String,
    paymentViewModel: PaymentViewModel
) {
    val paymentName = if (paymentMethod == "bk") "bKash" else "Nagad"

    val pageHeight = 1120
    val pageWidth = 792


    lateinit var bmp: Bitmap
    lateinit var bmp_bank: Bitmap
    lateinit var bmp_success: Bitmap

    val pdfDocument: PdfDocument = PdfDocument()

    val paint: Paint = Paint()
    val title: Paint = Paint()

    if (paymentMethod == "bk") {
        bmp = BitmapFactory.decodeResource(context.resources, R.drawable.bkash_money_send_icon)
    }

    if (paymentMethod == "ng") {
        bmp = BitmapFactory.decodeResource(context.resources, R.drawable.ic_nagad)
    }

    bmp_bank = BitmapFactory.decodeResource(context.resources, R.drawable.cbbl_logo)
    bmp_success = BitmapFactory.decodeResource(context.resources, R.drawable.successseal)


    val scaled_bmp: Bitmap = Bitmap.createScaledBitmap(bmp, 170, 130, false)
    val scaled_bmp_bank: Bitmap = Bitmap.createScaledBitmap(bmp_bank, 50, 130, false)
    val scaled_bmp_success: Bitmap = Bitmap.createScaledBitmap(bmp_success, 180, 150, false)


    val myPageInfo: PdfDocument.PageInfo? = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()
    val myPage: PdfDocument.Page = pdfDocument.startPage(myPageInfo)
    val canvas: Canvas = myPage.canvas

    canvas.drawBitmap(scaled_bmp, 56F, 40F, paint)
    canvas.drawBitmap(scaled_bmp_bank, 500F, 40F, paint)

    title.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
    title.textSize = 15F
    title.color = ContextCompat.getColor(context, R.color.gray)

    canvas.drawText("$paymentName fund transfer", 56F, 180F, title)

    title.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)

    title.color = ContextCompat.getColor(context, R.color.logo_color)
    title.textSize = 20F

    canvas.drawText("Community Bank", 550F, 100F, title)
    title.color = ContextCompat.getColor(context, R.color.gray)
    title.textSize = 12F
    canvas.drawText(USERADDRESS, 560F, 150F, title)

    title.textAlign = Paint.Align.CENTER
    title.textSize = 20F
    title.color = ContextCompat.getColor(context, R.color.black)
    canvas.drawText("COMMUNITY CASHT", 396F, 260F, title)
    canvas.drawText("TRANSACTION RECEIPT", 396F, 280F, title)

    title.textAlign = Paint.Align.LEFT
    title.color = ContextCompat.getColor(context, R.color.gray)
    canvas.drawText("Source Account/Card", 56F, 350F, title)
    canvas.drawText("0018228472", 580F, 350F, title)

    canvas.drawText("Amount", 56F, 390F, title)
    canvas.drawText("BTD ${paymentViewModel.amount}", 580F, 390F, title)

    canvas.drawText("Transaction Date time", 56F, 420F, title)
    val timeDate = getCurrentDate()
    canvas.drawText(timeDate, 580F, 420F, title)

    canvas.drawText("Narration", 56F, 450F, title)
    canvas.drawText(paymentViewModel.narration, 580F, 450F, title)



    canvas.drawText("Transaction Info", 56F, 520F, title)

    canvas.drawText("$paymentName number", 56F, 560F, title)
    canvas.drawText(paymentViewModel.number, 580F, 560F, title)

    canvas.drawText("Reference No", 56F, 590F, title)
    canvas.drawText("TXN1685", 580F, 590F, title)

    canvas.drawText("Total", 56F, 620F, title)
    canvas.drawText(paymentViewModel.amount, 580F, 620F, title)

    canvas.drawBitmap(scaled_bmp_success, 550F, 670F, paint)


    pdfDocument.finishPage(myPage)

    val file: File = File(Environment.getExternalStorageDirectory(), "Documents/payment_details.pdf")
    try {
        pdfDocument.writeTo(FileOutputStream(file))
        Toast.makeText(context, "PDF file generated..", Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {

        e.printStackTrace()
        Toast.makeText(context, "Fail to generate PDF file..", Toast.LENGTH_SHORT)
            .show()
    }
    pdfDocument.close()
}

fun checkPermissions(context: Context): Boolean {

    var writeStoragePermission = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    var readStoragePermission = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    return writeStoragePermission == PackageManager.PERMISSION_GRANTED && readStoragePermission == PackageManager.PERMISSION_GRANTED
}

fun requestPermission(activity: Activity) {
    ActivityCompat.requestPermissions(
        activity,
        arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ), 101
    )
}

