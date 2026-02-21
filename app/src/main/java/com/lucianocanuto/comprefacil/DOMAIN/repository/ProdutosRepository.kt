package com.lucianocanuto.comprefacil.DOMAIN.repository

import com.lucianocanuto.comprefacil.DOMAIN.model.Produto

interface ProdutosRepository {
    suspend fun buscarProdutos() : List<Produto>
}