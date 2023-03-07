package uz.gita.test_katta_bazar.navigation

import android.util.Log
import cafe.adriel.voyager.androidx.AndroidScreen
import kotlinx.coroutines.flow.MutableSharedFlow
import uz.gita.test_katta_bazar.utils.NavigatorArg
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationDispatcher @Inject constructor() : AppNavigation, NavigationHandler {

    init {
        Log.d("PPP", ":NavigationDispatcher ")
    }
    override suspend fun navigateTo(screen: AndroidScreen) = navigator {

        push(screen)
    }

    override suspend fun back() = navigator {
        pop()
    }

    override suspend fun replace(screen: AndroidScreen) = navigator {
        Log.d("PPP", "replace: ")
        replace(screen)
    }

    override val navStack = MutableSharedFlow<NavigatorArg>()


    private suspend fun navigator(ary: NavigatorArg) {
        navStack.emit(ary)
    }
}