package uz.gita.test_katta_bazar.domain.use_case.impl

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.test_katta_bazar.data.moddel.TechModel
import uz.gita.test_katta_bazar.data.remote.dto.TechResponse
import uz.gita.test_katta_bazar.domain.repository.Repository
import uz.gita.test_katta_bazar.domain.use_case.GetOffersUseCase
import uz.gita.test_katta_bazar.utils.ResultData
import java.net.ConnectException
import javax.inject.Inject

class GetOffersUseCaseImpl @Inject constructor(

    private val repository: Repository
) : GetOffersUseCase {
    override fun invoke(): Flow<ResultData<TechModel>> = flow<ResultData<TechModel>> {
        val result = repository.getOffers()
        Log.d("KKK", "$result: ")

        when (result) {
            is ResultData.Error -> {
                emit(ResultData.Error(result.error))
            }
            is ResultData.Message -> {
                emit(ResultData.Message(result.message))
            }
            is ResultData.Success -> {
                emit(ResultData.Success(result.data))

            }
        }
    }.catch {
        if (it is ConnectException) {
            emit(ResultData.Message("Internet Mavjut emas"))
        }
    }

        .flowOn(Dispatchers.IO)

    init {

        Log.d("MMM", ":uytrew ")
    }
}