package com.joseluisquintana.napptilustest.app.di

import com.joseluisquintana.data.OompaLoompa.CacheOompaLoompaDataSource
import com.joseluisquintana.data.OompaLoompa.MemoryCacheOompaLoompaDataSource
import com.joseluisquintana.data.OompaLoompa.NetworkOompaLoompaDataSource
import com.joseluisquintana.data.OompaLoompa.OompaLoompaRepository
import com.joseluisquintana.napptilustest.app.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ApplicationScope
@Module
class DataModule {

    val BASE_URL = "https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/"

    @Provides
    internal fun providesRetrofit(retrofit: Retrofit): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    internal fun provideOompaLoompaRepository(networkDataSource: NetworkOompaLoompaDataSource,
                                              cacheDataSource: CacheOompaLoompaDataSource): OompaLoompaRepository {
        return OompaLoompaRepository(networkDataSource, cacheDataSource)
    }

    @Provides
    internal fun providesNetworkOompaLoompaDataSource(retrofit: Retrofit): NetworkOompaLoompaDataSource {
        return NetworkOompaLoompaDataSource(retrofit)
    }

    @Provides
    internal fun providesCacheOompaLoompaDataSource(): CacheOompaLoompaDataSource {
        return MemoryCacheOompaLoompaDataSource()
    }
}
