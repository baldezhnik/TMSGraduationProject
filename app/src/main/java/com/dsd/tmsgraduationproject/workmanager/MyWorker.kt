package com.dsd.tmsgraduationproject.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.dsd.tmsgraduationproject.notification.Notification

class MyWorker(context: Context, workerParams: WorkerParameters): Worker(context, workerParams) {

    override fun doWork(): Result {
    return  try{
        val notification = Notification(applicationContext)
        notification.createNotificationChannel()
        notification.showNotification()
        Result.success()
      } catch (exception: Exception){
          exception.printStackTrace()
          Result.failure()
      }
    }
}