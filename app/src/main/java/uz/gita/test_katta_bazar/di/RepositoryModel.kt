package uz.gita.test_katta_bazar.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.test_katta_bazar.domain.repository.RepositoryImpl
import uz.gita.test_katta_bazar.domain.repository.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModel {


    @Singleton
    @Binds
    fun bindsRepo(impl: RepositoryImpl): Repository

}