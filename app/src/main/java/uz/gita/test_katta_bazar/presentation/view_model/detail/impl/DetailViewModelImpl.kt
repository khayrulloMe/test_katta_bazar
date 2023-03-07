package uz.gita.test_katta_bazar.presentation.view_model.detail.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.test_katta_bazar.navigation.AppNavigation
import uz.gita.test_katta_bazar.presentation.view_model.detail.DetailIntent
import uz.gita.test_katta_bazar.presentation.view_model.detail.DetailSideEffect
import uz.gita.test_katta_bazar.presentation.view_model.detail.DetailUiState
import uz.gita.test_katta_bazar.presentation.view_model.detail.DetailViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModelImpl @Inject constructor(
    private val appNavigation: AppNavigation
) : DetailViewModel, ViewModel() {
    override fun onEventDispatcher(intent: DetailIntent) = intent {
        when (intent) {
            DetailIntent.Back -> {
                appNavigation.back()
            }
        }
    }

    override val container: Container<DetailUiState, DetailSideEffect> = container(DetailUiState.Detail)

}