@file:Suppress("DEPRECATION")

package com.raya.agriapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.location.LocationRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.location.LocationManagerCompat.requestLocationUpdates
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var mfusedlocation :FusedLocationProviderClient
    private var myRequestCode=1010
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    mfusedlocation = LocationServices.getFusedLocationProviderClient(this)

    getLastLocation()
}

@SuppressLint("MissingPermission", "SuspiciousIndentation")
private fun getLastLocation() {
    if(checkPermission()){
        if(locationEnable()){
            mfusedlocation.lastLocation.addOnCompleteListener{
                    task->
                var location:Location?=task.result
                if(location==null){
                    newLocation()
                }else{
                    var handler=Handler(Looper.getMainLooper())
                    val postDelayed = handler.postDelayed({
                        val intent = Intent(this,MainPage::class.java)
                        intent.putExtra("Latitude",location.latitude.toString())
                        intent.putExtra("Longitude",location.longitude.toString())
                        startActivity(intent)
                        finish()
                    },2000)

                }
            }
        }else{
            Toast.makeText(this,"Please turn on your location",Toast.LENGTH_LONG).show()

        }
    }else{
        RequestPermission()
    }
}

@SuppressLint("MissingPermission")
private fun newLocation() {
    var locationRequest = com.google.android.gms.location.LocationRequest()
    locationRequest.priority =LocationRequest.QUALITY_BALANCED_POWER_ACCURACY
    locationRequest.interval=0
    locationRequest.fastestInterval=0
    locationRequest.numUpdates=1
    mfusedlocation=LocationServices.getFusedLocationProviderClient(this)
    mfusedlocation.requestLocationUpdates(locationRequest,locationCallback,Looper.myLooper())

}
private val locationCallback=object:LocationCallback(){
    override fun onLocationResult(p0: LocationResult) {
        var lastlocation: Location? =p0.lastLocation
    }
}

private fun locationEnable(): Boolean {
    var locationManager=getSystemService(Context.LOCATION_SERVICE)as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

}

private fun RequestPermission() {
    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION),myRequestCode)
}

private fun checkPermission(): Boolean {
    if(
        ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED||
        ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED)
    {
        return true
    }
    return false

}

override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    if(requestCode==myRequestCode){
        if(grantResults.isNotEmpty()&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
            getLastLocation()
        }
    }
}
}