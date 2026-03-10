package com.lucianocanuto.comprefacil.DATA.repository

import com.lucianocanuto.comprefacil.DATA.mapper.toEndereco
import com.lucianocanuto.comprefacil.DATA.remote.ViaCepAPI
import com.lucianocanuto.comprefacil.DOMAIN.model.Endereco
import javax.inject.Inject

class EnderecoRepository @Inject constructor(
    private val viaCepApi: ViaCepAPI
){

    suspend fun buscarCep(cep: String): Endereco{
        val dto = viaCepApi.buscarCep(cep)
        return dto.toEndereco()
    }


}