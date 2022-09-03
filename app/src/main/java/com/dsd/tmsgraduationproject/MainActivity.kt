package com.dsd.tmsgraduationproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dsd.tmsgraduationproject.notification.Notification
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navBottom: BottomNavigationView = findViewById(R.id.nav_bottom)
        val navController = findNavController(R.id.nav_host)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.wallet_fragment,
            R.id.operation_fragment,
            R.id.exchange_fragment
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navBottom.setupWithNavController(navController)
    }

    override fun onStart() {
        super.onStart()
        val notification = Notification(this)
        notification.createNotificationChannel()
        notification.showNotification()
    }
}