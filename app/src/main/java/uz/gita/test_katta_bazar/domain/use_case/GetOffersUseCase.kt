package uz.gita.test_katta_bazar.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.gita.test_katta_bazar.data.moddel.TechModel
import uz.gita.test_katta_bazar.data.remote.dto.TechResponse
import uz.gita.test_katta_bazar.utils.ResultData

interface GetOffersUseCase {
    operator fun invoke(): Flow<ResultData<TechModel>>
}