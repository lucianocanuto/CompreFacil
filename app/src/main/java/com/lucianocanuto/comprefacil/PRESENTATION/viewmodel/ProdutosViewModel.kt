package com.lucianocanuto.comprefacil.PRESENTATION.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucianocanuto.comprefacil.DOMAIN.repository.ProdutosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProdutosViewModel @Inject constructor(
    private val produtosRepository: ProdutosRepository
): ViewModel(){

    private val produtoObservavel = MutableLiveData<ProdutosRepository>()
    val produto : LiveData<ProdutosRepository> = produtoObservavel

    fun buscarProduto(){

    }

}