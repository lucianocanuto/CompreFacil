package com.lucianocanuto.comprefacil.DATA.repository

import com.lucianocanuto.comprefacil.DATA.RoomDataBase.CarrinhoDao
import com.lucianocanuto.comprefacil.DATA.RoomDataBase.CarrinhoItem
import javax.inject.Inject

class CarrinhoRepository @Inject constructor(
    private val carrinhoDao: CarrinhoDao
){

    suspend fun adicionarProduto( item: CarrinhoItem ){
        carrinhoDao.adicionarProduto(item)
    }
    suspend fun buscarProdutoCarrinho( id: Int ): CarrinhoItem? {
        return carrinhoDao.buscarProdutoCarrinho(id)
    }
    suspend fun atualizarQuantidade ( id: Int, quantidade: Int ){
        carrinhoDao.atualizarQuantidade(id,quantidade)
    }
    suspend fun listarCarrinho(): List<CarrinhoItem>{
        return carrinhoDao.listarCarrinho()
    }

}