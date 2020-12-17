package com.setel.utils.listener

import android.app.AlertDialog

interface SingleOptionResponseListener {
    fun positive(alertDialog: AlertDialog?)
}