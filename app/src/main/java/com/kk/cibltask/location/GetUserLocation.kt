package com.kk.cibltask.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.kk.cibltask.MainActivity
import com.kk.cibltask.navigation.USERADDRESS

import java.util.*


@SuppressLint("MissingPermission", "SetTextI18n")
fun getUserLocation(
    mFusedLocationClient: FusedLocationProviderClient,
    context: MainActivity,
) {

    if (checkPermissions(context)) {
        mFusedLocationClient.lastLocation.addOnCompleteListener(context) { task ->
            val location: Location? = task.result
            if (location != null) {
                val geocoder = Geocoder(context, Locale.getDefault())
                val address: MutableList<Address>? =
                    geocoder.getFromLocation(location.latitude, location.longitude, 1)

                Log.d("Address", address?.get(0)?.getAddressLine(0).toString())


                USERADDRESS = address?.get(0)?.getAddressLine(0).toString()
            }
        }
    }


}


private fun checkPermissions(context: Context): Boolean {
    if (ActivityCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        return true
    }
    return false
}


