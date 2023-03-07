package uz.gita.test_katta_bazar.navigation

import kotlinx.coroutines.flow.Flow
import uz.gita.test_katta_bazar.utils.NavigatorArg
import javax.inject.Singleton

@Singleton
interface NavigationHandler {
    val navStack: Flow<NavigatorArg>
}