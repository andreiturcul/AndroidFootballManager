package com.example.footballmanager.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

object TeamNotificationHelper {
    const val CHANNEL_ID = "team_actions"
    private const val CHANNEL_NAME = "Team actions"
    private var nextId = 1001

    fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Signed players, budget updates, and team changes"
            }
            context.getSystemService(NotificationManager::class.java)
                .createNotificationChannel(channel)
        }
    }

    fun notifyPlayerSigned(context: Context, playerName: String, price: Double, remaining: Double) {
        val remainingText = if (remaining >= 0) {
            "Remaining budget: €%.1fM".format(remaining)
        } else {
            "Over budget by €%.1fM".format(-remaining)
        }
        show(
            context = context,
            title = "Signed $playerName",
            body = "Fee: €%.1fM. $remainingText".format(price)
        )
    }

    fun notifyTeamSaved(context: Context, remaining: Double) {
        show(
            context = context,
            title = "Team saved",
            body = "Remaining budget: €%.1fM".format(remaining.coerceAtLeast(0.0))
        )
    }

    private fun show(context: Context, title: String, body: String) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.star_on)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()
        try {
            NotificationManagerCompat.from(context).notify(nextId++, notification)
        } catch (_: SecurityException) {
            // POST_NOTIFICATIONS denied — in-app snackbar still shows.
        }
    }
}
