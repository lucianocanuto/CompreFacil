package com.lucianocanuto.comprefacil.PRESENTATION.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucianocanuto.comprefacil.DOMAIN.model.Produto
import com.lucianocanuto.comprefacil.DOMAIN.repository.ProdutosRepository
import com.lucianocanuto.comprefacil.UTIL.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProdutosViewModel @Inject constructor(
    private val produtosRepository: ProdutosRepository
): ViewModel(){

    private val produtoObservalvel = MutableLiveData<Resource<List<Produto>>>()
    val produto : LiveData<Resource<List<Produto>>> = produtoObservalvel

    suspend fun buscarProduto(){

        produtoObservalvel.value = Resource.Carregando()

        viewModelScope.launch {

            try {
                val resultado = produtosRepository.buscarProdutos()
                produtoObservalvel.value = Resource.Sucesso(resultado)

            }catch ( erro : Exception){
                produtoObservalvel.value = Resource.Erro("Erro ao carregar os produtos!!!")
            }

        }

    }

}