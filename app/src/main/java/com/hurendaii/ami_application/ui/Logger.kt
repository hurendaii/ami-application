// Logger.kt
package com.hurendaii.ami_application.util

import android.util.Log

object Logger {
    private const val TAG = "AmiAppDebug"

    fun d(message: String) {
        Log.d(TAG, message)
    }
}
