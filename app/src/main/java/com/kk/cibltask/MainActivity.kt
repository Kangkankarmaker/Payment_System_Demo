package com.kk.cibltask

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.kk.cibltask.pdf.checkPermissions
import com.kk.cibltask.ui.theme.CIBLTaskTheme
import com.kk.cibltask.location.getUserLocation
import com.kk.cibltask.navigation.RootNavigationGraph

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CIBLTaskTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    if (!checkPermissions(applicationContext)) {
                        requestPermission(this)
                    }

                    val mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
                    getUserLocation(mFusedLocationClient, this)


                    val navController = rememberNavController()
                    RootNavigationGraph(navController = navController)

                }
            }
        }
    }


    private fun requestPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,

            ), 101
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // on below line we are checking if the
        // request code is equal to permission code.
        if (requestCode == 101) {

            // on below line we are checking if result size is > 0
            if (grantResults.isNotEmpty()) {

                // on below line we are checking if both the permissions are granted.
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    // if permissions are granted we are displaying a toast message.
                    Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show()
                    //getLocation(mFusedLocationClient, this)

                } else {

                    // if permissions are not granted we are
                    // displaying a toast message as permission denied.
                    Toast.makeText(this, "Permission Denied..", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }


}

