package com.lucianocanuto.comprefacil.DOMAIN.model

data class Carrinho(
    val produtoId: Int,
    val titulo: String,
    val preco: Double,
    val imagem: String,
    val quantidade: Int
)
