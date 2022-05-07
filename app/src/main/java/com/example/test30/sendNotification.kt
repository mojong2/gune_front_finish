package com.example.test30

import android.content.ContentValues.TAG
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
    try {
        val response = RetrofitInstance.api.postNotification(notification)
        if(response.isSuccessful) {
            Log.d(TAG, "Response: ${Gson().toJson(response)}")
        } else {
            Log.e(TAG, response.errorBody().toString())
        }
    } catch(e: Exception) {
        Log.e(TAG, e.toString())
    }
}