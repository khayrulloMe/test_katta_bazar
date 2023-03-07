package uz.gita.test_katta_bazar.domain.repository

import uz.gita.test_katta_bazar.data.moddel.TechModel
import uz.gita.test_katta_bazar.utils.ResultData

interface Repository {
    suspend fun getOffers():ResultData<TechModel>
}