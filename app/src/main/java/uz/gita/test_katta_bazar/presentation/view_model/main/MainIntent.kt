package uz.gita.test_katta_bazar.presentation.view_model.main

sealed interface MainIntent {
object GetDataRequest: MainIntent
}