package com.example.feature.permission.di

import com.example.feature.permission.PermissionViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val permissionModule = module {
    viewModelOf(::PermissionViewModel)
}