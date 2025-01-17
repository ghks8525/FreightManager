package com.jungbang.transitionmanager

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.location.Location
import android.location.LocationListener
import android.media.RingtoneManager
import android.net.Uri
import android.os.*
import android.util.Log
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.tasks.OnCanceledListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.jungbang.freightmanager.Utils.Trace
import com.jungbang.transitionmanager.ui.MainActivity
import java.util.concurrent.TimeUnit

class BackgroundLocationUpdateService : Service(), GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener, LocationListener {
    /* Declare in manifest
    <service android:name=".BackgroundLocationUpdateService"/>
    */
    private val TAG = "BackgroundLocationUpdateService"
    private val TAG_LOCATION = "TAG_LOCATION"
    private var context: Context? = null
    private var stopService = false

    /* For Google Fused API */
    protected var mGoogleApiClient: GoogleApiClient? = null
    protected var mLocationSettingsRequest: LocationSettingsRequest? = null
    private var latitude = "0.0"
    private var longitude = "0.0"
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var mSettingsClient: SettingsClient? = null
    private var mLocationCallback: LocationCallback? = null
    private var mLocationRequest: LocationRequest? = null
    private var mCurrentLocation: Location? = null

    /* For Google Fused API */
    override fun onCreate() {
        super.onCreate()
        context = this
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        StartForeground()
        val handler = Handler()
        val runnable: Runnable = object : Runnable {
            override fun run() {
                try {
                    if (!stopService) {
                        Trace.debug("aaaaaaaaaaaaaaaaaaaaaaaa = $latitude")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    if (!stopService) {
                        handler.postDelayed(this, TimeUnit.SECONDS.toMillis(10))
                    }
                }
            }
        }
        handler.postDelayed(runnable, 2000)
        buildGoogleApiClient()
        return START_STICKY
    }

    @SuppressLint("LongLogTag")
    override fun onDestroy() {
        Log.e(TAG, "Service Stopped")
        stopService = true
        if (mFusedLocationClient != null) {
            mFusedLocationClient!!.removeLocationUpdates(mLocationCallback)
            Log.e(TAG_LOCATION, "Location Update Callback Removed")
        }
        super.onDestroy()
    }

    @Nullable
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun StartForeground() {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0 /* Request code */,
            intent,
            PendingIntent.FLAG_ONE_SHOT
        )
        val CHANNEL_ID = "channel_location"
        val CHANNEL_NAME = "channel_location"
        var builder: NotificationCompat.Builder? = null
        val notificationManager = applicationContext
            .getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            notificationManager.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(
                applicationContext,
                CHANNEL_ID
            )
            builder.setChannelId(CHANNEL_ID)
            builder.setBadgeIconType(NotificationCompat.BADGE_ICON_NONE)
        } else {
            builder = NotificationCompat.Builder(
                applicationContext,
                CHANNEL_ID
            )
        }
        builder.setContentTitle("Your title")
        builder.setContentText("You are now online")
        val notificationSound: Uri =
            RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION)
        builder.setSound(notificationSound)
        builder.setAutoCancel(true)
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setContentIntent(pendingIntent)
        val notification: Notification = builder.build()
        startForeground(101, notification)
    }

    override fun onLocationChanged(location: Location) {
        Log.e(
            TAG_LOCATION,
            "Location Changed Latitude : " + location.getLatitude()
                .toString() + "\tLongitude : " + location.getLongitude()
        )
        latitude = java.lang.String.valueOf(location.getLatitude())
        longitude = java.lang.String.valueOf(location.getLongitude())
        if (latitude.equals("0.0", ignoreCase = true) && longitude.equals(
                "0.0",
                ignoreCase = true
            )
        ) {
            requestLocationUpdate()
        } else {
            Log.e(
                TAG_LOCATION,
                "Latitude : " + location.getLatitude()
                    .toString() + "\tLongitude : " + location.getLongitude()
            )
        }
    }


    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    override fun onProviderEnabled(provider: String) {}
    override fun onProviderDisabled(provider: String) {}


    override fun onConnected(@Nullable bundle: Bundle?) {
        mLocationRequest = LocationRequest()
        mLocationRequest!!.interval = 10 * 1000
        mLocationRequest!!.setFastestInterval(5 * 1000)
        mLocationRequest!!.setPriority(PRIORITY_HIGH_ACCURACY)
        val builder: LocationSettingsRequest.Builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest)
        builder.setAlwaysShow(true)
        mLocationSettingsRequest = builder.build()
        mSettingsClient!!.checkLocationSettings(mLocationSettingsRequest)
            .addOnSuccessListener(OnSuccessListener<Any?> {
                Log.e(TAG_LOCATION, "GPS Success")
                requestLocationUpdate()
            }).addOnFailureListener(OnFailureListener { e ->
                val statusCode = (e as ApiException).statusCode
                when (statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                        val REQUEST_CHECK_SETTINGS = 214
                        val rae = e as ResolvableApiException
                        rae.startResolutionForResult(
                            context as AppCompatActivity?,
                            REQUEST_CHECK_SETTINGS
                        )
                    } catch (sie: IntentSender.SendIntentException) {
                        Log.e(TAG_LOCATION, "Unable to execute request.")
                    }
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> Log.e(
                        TAG_LOCATION,
                        "Location settings are inadequate, and cannot be fixed here. Fix in Settings."
                    )
                }
            }).addOnCanceledListener(OnCanceledListener {
                Log.e(
                    TAG_LOCATION,
                    "checkLocationSettings -> onCanceled"
                )
            })
    }

    override fun onConnectionSuspended(i: Int) {
        connectGoogleClient()
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        buildGoogleApiClient()
    }

    @Synchronized
    protected fun buildGoogleApiClient() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        mSettingsClient = LocationServices.getSettingsClient(context)
        mGoogleApiClient = GoogleApiClient.Builder(context)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build()
        connectGoogleClient()
        mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                Log.e(TAG_LOCATION, "Location Received")
                mCurrentLocation = locationResult.lastLocation
                onLocationChanged(mCurrentLocation!!)
            }
        }
    }

    private fun connectGoogleClient() {
        val googleAPI: GoogleApiAvailability = GoogleApiAvailability.getInstance()
        val resultCode: Int = googleAPI.isGooglePlayServicesAvailable(context)
        if (resultCode == ConnectionResult.SUCCESS) {
            mGoogleApiClient?.connect()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdate() {
        mFusedLocationClient?.requestLocationUpdates(
            mLocationRequest,
            mLocationCallback,
            Looper.myLooper()
        )
    }
}