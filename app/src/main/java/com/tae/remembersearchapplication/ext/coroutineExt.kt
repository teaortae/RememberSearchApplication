package com.tae.remembersearchapplication.ext

import com.tae.baselibrary.api.ApiResult
import com.tae.baselibrary.util.Log
import com.tae.remembersearchapplication.RememberApp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

inline fun <reified T : Any> Flow<T?>.loading(): Flow<T?> {
    val dialog = RememberApp.INSTANCE.progressDialog
    return onStart { dialog?.show() }
        .onCompletion {
            dialog?.dismiss()
        }
        .catch {
            Log.d(it.message.toString())
            emit(null)
            dialog?.dismiss()
        }
}