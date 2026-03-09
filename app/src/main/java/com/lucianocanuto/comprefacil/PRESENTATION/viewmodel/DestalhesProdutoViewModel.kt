package com.lucianocanuto.comprefacil.PRESENTATION.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucianocanuto.comprefacil.DATA.RoomDataBase.CarrinhoItem
import com.lucianocanuto.comprefacil.DATA.repository.CarrinhoRepository
import com.lucianocanuto.comprefacil.DOMAIN.model.Produto
import com.lucianocanuto.comprefacil.DOMAIN.repository.ProdutosRepository
import com.lucianocanuto.comprefacil.UTIL.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DestalhesProdutoViewModel @Inject constructor(
    private val repository : ProdutosRepository,
    private val carrinhoRepository: CarrinhoRepository
): ViewModel(){

    private val observalvel = MutableLiveData<Resource<Produto>>()
    val produtoDt : LiveData<Resource<Produto>> = observalvel


    fun buscarProdutoDt(id: Int){

        observalvel.value = Resource.Carregando()

        viewModelScope.launch{


            try {
                val dtProduto = repository.buscarProdutoPorId(id)
                observalvel.value = Resource.Sucesso (dtProduto)

            }catch (e: Exception) {
                observalvel.value = Resource.Erro(
                    e.message ?: "Erro ao buscar produto"
                )
            }
        }

    }

    fun adicionarAoCarrinho (produto: Produto){

        viewModelScope.launch {

            val produtoCarrinho = carrinhoRepository.buscarProdutoCarrinho(produto.id)

            if (produtoCarrinho != null){
                val novaQuantidade = produtoCarrinho.quantidade + 1

                carrinhoRepository.atualizarQuantidade(
                    produto.id,
                    novaQuantidade
                )
            } else{
                val item = CarrinhoItem(
                    produtoId = produto.id,
                    titulo = produto.titulo,
                    preco = produto.preco,
                    imagem = produto.imagem,
                    quantidade = 1
                )
                carrinhoRepository.adicionarProduto(item)
            }

            val carrinho = carrinhoRepository.listarCarrinho()

            carrinho.forEach {

                Log.e(
                    "Carrinho_Debug",
                    "Produto: ${it.titulo} | Quantidade: ${it.quantidade}"
                )
            }

        }
    }
}