package uz.gita.test_katta_bazar.di

import android.content.Context
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.test_katta_bazar.BuildConfig
import uz.gita.test_katta_bazar.data.remote.api_service.ApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FeatureModel {


    @[Provides Singleton]
    fun provideOkHttp(@ApplicationContext context: Context) = OkHttpClient.Builder().addInterceptor { chain ->
        val request = chain.request().newBuilder().build()
        chain.proceed(request)
    }.build()

    @[Provides Singleton]
    fun provideRetrofit(client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).client(client).build()


    @[Provides Singleton]
    fun provideAuthApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun bindGson():Gson =Gson()

}
