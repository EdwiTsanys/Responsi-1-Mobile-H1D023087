package com.unsoed.responsi1mobileh1d023087.data.api

import com.unsoed.responsi1mobileh1d023087.data.model.Team
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @Headers("X-Auth-Token: d7ea7db9be644cac9b689ebbc4df2740")
    @GET("teams/{id}")
    suspend fun getTeam(@Path("id") id: Int): Team
}
