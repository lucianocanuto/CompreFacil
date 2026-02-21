package com.lucianocanuto.comprefacil.DATA.repository

import com.lucianocanuto.comprefacil.DATA.mapper.toDomain
import com.lucianocanuto.comprefacil.DATA.remote.DummyApi
import com.lucianocanuto.comprefacil.DOMAIN.model.Produto
import com.lucianocanuto.comprefacil.DOMAIN.repository.ProdutosRepository
import javax.inject.Inject

class ProdutosRepositoryImpl @Inject constructor(
    private val dummyApi: DummyApi
) : ProdutosRepository {
    override suspend fun buscarProdutos(): List<Produto> {
        return dummyApi.listarProdutos().products.map { dto->
            dto.toDomain()

        }
    }
}