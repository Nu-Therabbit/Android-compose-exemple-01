package com.example.proc01

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ScreenAViewModel : ViewModel() {
    fun showSnackbar() {
        viewModelScope.launch {
            SnackbarController.sendEvent(
                event = SnackbarEvent(
                    message = "Hello from ViewModel",
                    action = SnackbarAction(
                        name = "Click me!",
                        action = {
                            SnackbarController.sendEvent(
                                event = SnackbarEvent(
                                    message = "Action pressed!",
                                    action = SnackbarAction(
                                        name = "Action again",
                                        action = {
                                            Log.d("xxx", "Action again clicked.")
                                        },
                                    ),
                                ),
                            )
                        },
                    ),
                ),
            )
        }
    }
}