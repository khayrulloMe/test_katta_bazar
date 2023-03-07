package uz.gita.test_katta_bazar.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.test_katta_bazar.navigation.AppNavigation
import uz.gita.test_katta_bazar.navigation.NavigationDispatcher
import uz.gita.test_katta_bazar.navigation.NavigationHandler
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModel {

    @Binds
    fun navigation(navigator: NavigationDispatcher): AppNavigation

    @Binds
    fun navigatorHandler(navigatorHandler: NavigationDispatcher): NavigationHandler


}