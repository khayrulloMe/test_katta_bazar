package uz.gita.test_katta_bazar.presentation.view_model.main.impl

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.util.Log
import androidx.core.content.ContextCompat.registerReceiver
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
import uz.gita.test_katta_bazar.navigation.AppNavigation
import uz.gita.test_katta_bazar.presentation.screen.DetailScreen
import uz.gita.test_katta_bazar.presentation.view_model.main.MainIntent
import uz.gita.test_katta_bazar.presentation.view_model.main.MainSideEffect
import uz.gita.test_katta_bazar.presentation.view_model.main.MainUiState
import uz.gita.test_katta_bazar.presentation.view_model.main.MainViewModel
import uz.gita.test_katta_bazar.utils.InternetBroadCast
import uz.gita.test_katta_bazar.utils.ResultData
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(private val useCase: GetOffersUseCase, private val appNavigation: AppNavigation) :
    MainViewModel,
    ViewModel() {
    override val container: Container<MainUiState, MainSideEffect> = container(MainUiState.Loading())

    init {
        intent {
            reduce {
                Log.d("Shoxrux", "boshla")

                MainUiState.Loading().copy(isLoading = true)
            }


            useCase().collect {
                reduce {

                    MainUiState.Loading().copy(isLoading = false)
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


//    private fun myReduce(): {
//
//    }

    override fun onEventDispatcher(intent: MainIntent) = intent {


        when (intent) {

            MainIntent.GetDataRequest -> {
                reduce {
                    MainUiState.Loading().copy(isLoading = true)
                }

                useCase().collectLatest {
                    reduce {
                        MainUiState.Loading().copy(isLoading = false)
                    }

                    delay(100)
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
                                MainUiState.Loading(isLoading = false).copy(isLoading = false)
                            }
                            reduce {
                                MainUiState.Success(it.data)
                            }
                        }
                    }

                }
            }
            is MainIntent.GoToDetails -> {
                appNavigation.navigateTo(DetailScreen(intent.item))
            }
        }
    }

}