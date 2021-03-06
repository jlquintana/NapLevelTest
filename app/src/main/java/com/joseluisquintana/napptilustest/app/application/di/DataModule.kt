package com.joseluisquintana.napptilustest.app.application.di

import com.joseluisquintana.data.OompaLoompa.CacheOompaLoompaDataSource
import com.joseluisquintana.data.OompaLoompa.MemoryCacheOompaLoompaDataSource
import com.joseluisquintana.data.OompaLoompa.NetworkOompaLoompaDataSource
import com.joseluisquintana.data.OompaLoompa.OompaLoompaRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@ApplicationScope
@Module
class DataModule {

    val BASE_URL = "https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/"

    @ApplicationScope
    @Provides
    internal fun providesRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @ApplicationScope
    @Provides
    internal fun provideOompaLoompaRepository(networkDataSource: NetworkOompaLoompaDataSource,
                                              cacheDataSource: CacheOompaLoompaDataSource): OompaLoompaRepository {
        return OompaLoompaRepository(networkDataSource, cacheDataSource)
    }

    @ApplicationScope
    @Provides
    internal fun providesNetworkOompaLoompaDataSource(retrofit: Retrofit): NetworkOompaLoompaDataSource {
        return NetworkOompaLoompaDataSource(retrofit)
    }

    @ApplicationScope
    @Provides
    internal fun providesCacheOompaLoompaDataSource(): CacheOompaLoompaDataSource {
        return MemoryCacheOompaLoompaDataSource()
    }
}
