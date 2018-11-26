package com.marcgdiez.jsonplaceholder.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.marcgdiez.jsonplaceholder.core.NetworkConfig
import com.marcgdiez.jsonplaceholder.core.HeadersInterceptor
import com.marcgdiez.jsonplaceholder.datasource.NetworkDataSource
import com.marcgdiez.jsonplaceholder.detail.ItemDetailContract
import com.marcgdiez.jsonplaceholder.detail.ItemDetailPresenter
import com.marcgdiez.jsonplaceholder.detail.usecase.GetItemCommentsUseCase
import com.marcgdiez.jsonplaceholder.extensions.create
import com.marcgdiez.jsonplaceholder.list.ItemsListContract
import com.marcgdiez.jsonplaceholder.list.ItemsListPresenter
import com.marcgdiez.jsonplaceholder.list.usecase.GetItemsListUseCase
import com.marcgdiez.jsonplaceholder.repository.*
import com.marcgdiez.jsonplaceholder.repository.mapper.ItemsMapper
import com.readystatesoftware.chuck.ChuckInterceptor
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    factory { GetItemsListUseCase(get()) }
    factory { GetItemCommentsUseCase(get()) }
    factory<ItemsListContract.Presenter> { ItemsListPresenter(get(), Dispatchers.Main) }
    factory<ItemDetailContract.Presenter> { ItemDetailPresenter(get(), Dispatchers.Main) }
    single<ItemsRepository> { ItemsRepository.NetworkRepository(get()) }
    single { NetworkDataSource(get(), get()) }
    single { ItemsMapper() }
    single<ItemsApi> { get<Retrofit>().create() }

    single {
        Retrofit.Builder()
            .apply {
                addCallAdapterFactory(CoroutineCallAdapterFactory())
                addConverterFactory(GsonConverterFactory.create())
                baseUrl(NetworkConfig.API_URL)
                client(get())
            }
            .build()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<ChuckInterceptor>())
            .addInterceptor(get<HeadersInterceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single { ChuckInterceptor(androidContext()) }
    single { HeadersInterceptor() }
    factory {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return@factory interceptor
    }
}