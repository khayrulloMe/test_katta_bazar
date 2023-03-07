package uz.gita.test_katta_bazar.presentation.view_model.main

import uz.gita.test_katta_bazar.data.moddel.TechModel

sealed interface MainUiState {
    data class Success(val list:TechModel): MainUiState
    data class Loading(var isLoading: Boolean = false): MainUiState
    data class Message(val message:String): MainUiState

}