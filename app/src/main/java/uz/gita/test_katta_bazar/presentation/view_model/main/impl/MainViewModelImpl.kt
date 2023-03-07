package uz.gita.test_katta_bazar.presentation.view_model.main.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.test_katta_bazar.domain.use_case.GetOffersUseCase
import uz.gita.test_katta_bazar.presentation.view_model.main.MainIntent
import uz.gita.test_katta_bazar.presentation.view_model.main.MainSideEffect
import uz.gita.test_katta_bazar.presentation.view_model.main.MainUiState
import uz.gita.test_katta_bazar.presentation.view_model.main.MainViewModel
import uz.gita.test_katta_bazar.utils.ResultData
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(private val useCase: GetOffersUseCase) : MainViewModel, ViewModel() {
    override val container: Container<MainUiState, MainSideEffect> = container(MainUiState.Loading(isLoading = false))


    override fun onEventDispatcher(intent: MainIntent) = intent {
        when (intent) {

            MainIntent.GetDataRequest -> {
                reduce {
                    MainUiState.Loading(isLoading = true)
                }
                useCase().collect {
                    reduce {
                        MainUiState.Loading(isLoading = false)
                    }
                    when (it) {
                        is ResultData.Error -> {
                            if (it.error.message != null) {
                                reduce {
                                    MainUiState.Message(it.error.message!!)
                                }
                            }
                        }
                        is ResultData.Message -> {
                            reduce {
                                MainUiState.Message(it.message)
                            }
                        }
                        is ResultData.Success -> {
                            reduce {
                                MainUiState.Success(it.data)
                            }
                        }
                    }

                }
            }
        }
    }

}