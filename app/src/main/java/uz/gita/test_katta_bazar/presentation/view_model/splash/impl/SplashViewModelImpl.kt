package uz.gita.test_katta_bazar.presentation.view_model.splash.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.test_katta_bazar.navigation.AppNavigation
import uz.gita.test_katta_bazar.presentation.screen.MainScreen
import uz.gita.test_katta_bazar.presentation.view_model.splash.SplashIntent
import uz.gita.test_katta_bazar.presentation.view_model.splash.SplashSideEffect
import uz.gita.test_katta_bazar.presentation.view_model.splash.SplashUiState
import uz.gita.test_katta_bazar.presentation.view_model.splash.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val appNavigation: AppNavigation
) : SplashViewModel, ViewModel() {
    override val container: Container<SplashUiState, SplashSideEffect> = container(SplashUiState.Splash)

    init {
        viewModelScope.launch {
            delay(1200)
            appNavigation.replace(MainScreen())

        }

    }

    override fun onEventDispatcher(intent: SplashIntent) = intent {

    }

}