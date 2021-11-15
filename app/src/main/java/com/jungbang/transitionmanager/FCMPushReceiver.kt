package com.jungbang.transitionmanager

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.jungbang.freightmanager.Utils.Trace

class FCMPushReceiver : FirebaseMessagingService() {
    companion object {
        fun getToken(l: OnCompleteListener<String>) {
            FirebaseMessaging.getInstance().token.addOnCompleteListener(l)
        }
    }

    //TODO Step 3.2 log registration token
    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    override fun onNewToken(token: String) {
        Trace.debug("++ onNewToken() token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token)
    }

    /**
     * Persist token to third-party servers.
     *
     * @param token The new token.
     */
    private fun sendRegistrationToServer(token: String?) {
        // TODO: Implement this method to send token to your app server.
    }

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Trace.debug("++ onMessageReceived()")
        Trace.debug(">> remoteMessage.senderId = ${remoteMessage.senderId}")
        Trace.debug(">> remoteMessage.messageId = ${remoteMessage.messageId}")
        Trace.debug(">> remoteMessage.messageType = ${remoteMessage.messageType}")
        Trace.debug(">> remoteMessage.priority = ${remoteMessage.priority}")
        Trace.debug(">> remoteMessage.from = ${remoteMessage.from}")
        Trace.debug(">> remoteMessage.to = ${remoteMessage.to}")
        Trace.debug(">> remoteMessage.data = ${remoteMessage.data}")

        Trace.debug(">> notification.title = ${remoteMessage.notification?.title}")
        Trace.debug(">> notification.body = ${remoteMessage.notification?.body}")

//        >> remoteMessage.senderId = 377136314877
//        >> remoteMessage.messageId = 0:1619163031954173%597cadf3597cadf3
//        >> remoteMessage.messageType = null
//        >> remoteMessage.priority = 1
//        >> remoteMessage.from = 377136314877
//        >> remoteMessage.to = null
//        >> remoteMessage.data = {}
//        >> notification.title = 22222222
//        >> notification.body = 111111111111

        sendNotification(remoteMessage)
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private fun sendNotification(message: RemoteMessage) {
        startService(Intent(this, BackgroundLocationUpdateService::class.java))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val builder =
                NotificationCompat.Builder(this,"a")
                    .setAutoCancel(true)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(message.notification?.title)
                    .setContentText(message.notification?.body)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(0, builder.build())
        } else {
            val builder = NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(message.notification?.title)
                .setContentText(message.notification?.body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            with(NotificationManagerCompat.from(baseContext)) {
                notify(0, builder.build())
            }
        }
    }
}