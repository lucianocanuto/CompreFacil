package com.lucianocanuto.comprefacil.DATA.remote

import com.lucianocanuto.comprefacil.DATA.dto.Product
import com.lucianocanuto.comprefacil.DATA.dto.ProdutosResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DummyApi {

    @GET("products")
    suspend fun listarProdutos(): ProdutosResponse

    @GET("products/{id}")
    suspend fun  buscarProdutoPorId(
        @Path("id") id: Int
    ): Product
}