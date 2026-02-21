package com.lucianocanuto.comprefacil.DATA.dto

data class ProdutosResponse(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)