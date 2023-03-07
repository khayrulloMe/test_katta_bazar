package uz.gita.test_katta_bazar.presentation.view_model.main

import uz.gita.test_katta_bazar.data.moddel.OfferModel

sealed interface MainIntent {
object GetDataRequest: MainIntent
    data class GoToDetails( val item:OfferModel):MainIntent
}