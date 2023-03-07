package uz.gita.test_katta_bazar.data.remote.api_service

import retrofit2.Response
import retrofit2.http.GET
import uz.gita.test_katta_bazar.data.remote.dto.TechResponse
import uz.gita.test_katta_bazar.utils.ResultData

interface ApiService {

    @GET("offers")
    suspend fun getOffers(): Response<TechResponse>


}