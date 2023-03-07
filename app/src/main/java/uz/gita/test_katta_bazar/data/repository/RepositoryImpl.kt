package uz.gita.test_katta_bazar.data.repository

import android.util.Log
import com.google.gson.Gson
import uz.gita.test_katta_bazar.data.mapper.toModel
import uz.gita.test_katta_bazar.data.moddel.TechModel
import uz.gita.test_katta_bazar.data.remote.api_service.ApiService
import uz.gita.test_katta_bazar.data.remote.dto.ErrorResponseBody
import uz.gita.test_katta_bazar.data.remote.dto.TechResponse
import uz.gita.test_katta_bazar.domain.repository.Repository
import uz.gita.test_katta_bazar.utils.ResultData
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val gson: Gson
) : Repository {
    override suspend fun getOffers(): ResultData<TechModel> {

        val response = api.getOffers()
        if (response.isSuccessful) {
            response.body()?.let {
                return ResultData.Success(it.toModel())

            }
        } else {
            response.errorBody()?.apply {
                val errorResponse = gson.fromJson(this.string(), ErrorResponseBody::class.java)
                return ResultData.Message(errorResponse.message)
            }
        }
        return ResultData.Error(Exception("not found Exception"))
    }
}