package com.lucianocanuto.comprefacil.DATA.remote

import com.lucianocanuto.comprefacil.DATA.dto.viacep.EnderecoDTO
import com.lucianocanuto.comprefacil.DOMAIN.model.Endereco
import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepAPI {
    @GET("ws/{cep}/json/")
    suspend fun buscarCep(
        @Path("cep") cep: String
    ): EnderecoDTO
}