package com.appnroll.graphql.data

import com.appnroll.graphql.model.PokemonData
import io.github.wax911.library.annotation.GraphQuery
import io.github.wax911.library.model.body.GraphContainer
import io.github.wax911.library.model.request.QueryContainerBuilder
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface PokedexBackend {

    @POST("/")
    @GraphQuery("pokemon")
    @Headers("Content-Type: application/json")
    fun getPokemon(@Body request: QueryContainerBuilder): Call<GraphContainer<PokemonData>>

}
