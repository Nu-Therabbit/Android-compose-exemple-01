package com.example.feature.permission

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigation.Navigator
import com.example.navigation.Screen
import kotlinx.coroutines.launch

class PermissionViewModel(private val navigator: Navigator) : ViewModel() {

    fun navigateToSingleScreen() {
        viewModelScope.launch {
            navigator.navigate(Screen.PermissionScreen.SinglePermission)
        }
    }

    fun navigateToMultipleScreen() {
        viewModelScope.launch {
            navigator.navigate(Screen.PermissionScreen.MultiplePermission)
        }
    }
}