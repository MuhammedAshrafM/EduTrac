package com.invade.core.util

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.invade.core.network.error.ErrorEntity
import com.invade.core.network.error.handleError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


fun lifecycleScope(
    fragment: Fragment,
    block: suspend CoroutineScope.() -> Unit
){
    fragment.viewLifecycleOwner.lifecycleScope.launch {
        fragment.viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            block()
        }
    }
}

fun handleResponseError(context: Context, errorEntity: ErrorEntity?): String? {
    return handleError(context, errorEntity)
}

fun handleError(context: Context, errorEntity: ErrorEntity?): String? {
    return context.handleError(errorEntity)
}