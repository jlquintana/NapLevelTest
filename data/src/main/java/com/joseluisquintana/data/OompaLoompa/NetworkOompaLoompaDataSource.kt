package com.joseluisquintana.data.OompaLoompa

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class NetworkOompaLoompaDataSource(retrofit : Retrofit) : OompaLoompaDataSource {

    private val restRestApi: OompaLoompaRestApi by lazy {
        retrofit.create(OompaLoompaRestApi::class.java)
    }

    override fun getOompaLoompas(page: Int): Single<List<OompaLoompa>> {
        return restRestApi.getLoompas(page).map { response -> response.results }
    }

    override fun getOompaLoompa(id: Long): Single<OompaLoompa> {
        return restRestApi.getLoompa(id)
    }

    private interface OompaLoompaRestApi {
        @GET("oompa-loompas")
        fun getLoompas(@Query("page") page: Int): Single<OompaLoompaListResponse>


        @GET("oompa-loompas/{id}")
        fun getLoompa(@Path("id") id : Long): Single<OompaLoompa>
    }
}
