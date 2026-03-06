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
class DestalhesProdutoViewModel @Inject constructor(
    private val repository : ProdutosRepository
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



}