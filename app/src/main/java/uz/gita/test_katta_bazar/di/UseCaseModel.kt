package uz.gita.test_katta_bazar.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.test_katta_bazar.domain.use_case.GetOffersUseCase
import uz.gita.test_katta_bazar.domain.use_case.GetOffersUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModel {

    @Binds
    fun bindUseCase(impl: GetOffersUseCaseImpl):GetOffersUseCase
}