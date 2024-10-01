package com.example.navigation.di

import com.example.navigation.DefaultNavigator
import com.example.navigation.Graph
import com.example.navigation.Navigator
import org.koin.dsl.module

val navigatorModule = module {
    single<Navigator> { DefaultNavigator(startDestination = Graph.Home) }
}