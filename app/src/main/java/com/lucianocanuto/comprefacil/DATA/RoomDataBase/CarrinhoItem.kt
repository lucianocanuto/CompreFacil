package com.lucianocanuto.comprefacil.DATA.RoomDataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "carrinho")
data class CarrinhoItem(

    @PrimaryKey
    val produtoId: Int,
    val titulo: String,
    val preco: Double,
    val imagem: String,
    val quantidade: Int
)
