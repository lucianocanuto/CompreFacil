package com.lucianocanuto.comprefacil.DATA.RoomDataBase

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

interface CarrinhoDao {

    @Query("SELECT * FROM carrinho WHERE produtoId = :produtoId LIMIT 1")
    suspend fun buscarProdutoCarrinho(produtoId: Int): CarrinhoItem?

    @Insert
    suspend fun adicionarProduto( item: CarrinhoItem )

    @Query("UPDATE carrinho SET quantidade = :novaQuantidade WHERE produtoId = :produtoId")
    suspend fun atualizarQuantidade(produtoId: Int, novaQuantidade: Int)

    @Query("SELECT * FROM carrinho")
    suspend fun listarCarrinho(): List<CarrinhoItem>

    @Delete
    suspend fun removerProduto( item: CarrinhoItem )

    @Query("DELETE FROM carrinho")
    suspend fun limparCarrinho()

    @Query("SELECT SUM(preco * quantidade) FROM carrinho")
    suspend fun totalCarrinho(): Double

}