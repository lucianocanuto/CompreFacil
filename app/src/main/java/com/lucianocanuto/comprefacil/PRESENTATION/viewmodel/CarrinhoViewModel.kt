package com.lucianocanuto.comprefacil.PRESENTATION.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucianocanuto.comprefacil.DATA.RoomDataBase.CarrinhoItem
import com.lucianocanuto.comprefacil.DATA.repository.CarrinhoRepository
import com.lucianocanuto.comprefacil.DOMAIN.model.Carrinho
import com.lucianocanuto.comprefacil.UTIL.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CarrinhoViewModel @Inject constructor(
    private val carrinhoRepository : CarrinhoRepository
): ViewModel(){

    private val carrinhoObservavel = MutableLiveData<Resource<List<CarrinhoItem>>>()
    val listaCarrinho : LiveData<Resource<List<CarrinhoItem>>> = carrinhoObservavel

    private val totalCarrinhoObservavel = MutableLiveData<Double>()
    val totalCarrinho: LiveData<Double> = totalCarrinhoObservavel

    private val contadorCarrinhoObservavel = MutableLiveData<Int>()
    val contadorCarrinho: LiveData<Int> = contadorCarrinhoObservavel


    fun listarCarrinho(){

        carrinhoObservavel.value = Resource.Carregando()

        viewModelScope.launch {

            try {

                val retorno = carrinhoRepository.listarCarrinho()

                val total = retorno.sumOf { it.preco * it.quantidade }

                totalCarrinhoObservavel.value = total

                carrinhoObservavel.value = Resource.Sucesso(retorno)

                contadorCarrinhoObservavel.value =
                    retorno.sumOf { it.quantidade }



            }catch (e: Exception){

                carrinhoObservavel.value = Resource.Erro(
                    "Erro ao carregar carrinho"
                )

            }

        }
    }


    fun adicionarItem(item: CarrinhoItem){

        viewModelScope.launch {

            carrinhoRepository.adicionarProduto(item)

            listarCarrinho()

        }

    }


    fun removerItem(item: CarrinhoItem){

        viewModelScope.launch {

            carrinhoRepository.removerProduto(item)

            listarCarrinho()

        }

    }


    fun limparCarrinho(){

        viewModelScope.launch {

            carrinhoRepository.limparCarrinho()

            listarCarrinho()

        }

    }

}