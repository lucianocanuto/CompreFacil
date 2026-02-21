package com.lucianocanuto.comprefacil.DATA.remote

import com.lucianocanuto.comprefacil.DATA.dto.ProdutosResponse
import retrofit2.http.GET

interface DummyApi {

    @GET("products")
    suspend fun listarProdutos(): ProdutosResponse
}