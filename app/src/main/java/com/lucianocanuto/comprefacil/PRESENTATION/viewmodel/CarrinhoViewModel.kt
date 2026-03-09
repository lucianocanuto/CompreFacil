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


    private val carrinhoObsaervavel = MutableLiveData<Resource<List<CarrinhoItem>>>()
    val listaCarrinho : LiveData<Resource<List<CarrinhoItem>>> = carrinhoObsaervavel

    private val totalCarrinhoObservavel = MutableLiveData<Double>()
    val totalCarrinho: LiveData<Double> = totalCarrinhoObservavel


    fun listarCarrinho(){

        carrinhoObsaervavel.value = Resource.Carregando()

        viewModelScope.launch {

            try {
                val retorno = carrinhoRepository.listarCarrinho()
                val total = retorno.sumOf { it.preco * it.quantidade }
                totalCarrinhoObservavel.value = total
                carrinhoObsaervavel.value = Resource.Sucesso(retorno)

            }catch (e: Exception){
                carrinhoObsaervavel.value = Resource.Erro(
                    "Erro ao listar ao carregar os produtos do carrinho!"
                )
            }

        }
    }
    fun removerItem(item: CarrinhoItem){
        viewModelScope.launch {
            carrinhoRepository.removerProduto(item)
            listarCarrinho()
        }

    }



}