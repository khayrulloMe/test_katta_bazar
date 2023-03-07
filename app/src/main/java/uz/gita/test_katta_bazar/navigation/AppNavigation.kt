package uz.gita.test_katta_bazar.navigation

import cafe.adriel.voyager.androidx.AndroidScreen
import kotlinx.coroutines.flow.Flow
import uz.gita.test_katta_bazar.utils.NavigatorArg
import javax.inject.Singleton

@Singleton
interface AppNavigation {
    suspend fun navigateTo(screen: AndroidScreen)
    suspend fun back()
    suspend fun replace(screen: AndroidScreen)
}