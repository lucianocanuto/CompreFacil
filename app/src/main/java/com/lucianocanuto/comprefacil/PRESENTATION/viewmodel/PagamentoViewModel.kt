package com.lucianocanuto.comprefacil.PRESENTATION.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucianocanuto.comprefacil.UTIL.PagamentoStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PagamentoViewModel @Inject constructor(): ViewModel() {

    private val pagamentoObservavel = MutableLiveData<PagamentoStatus>()
    val statusPagamento : LiveData<PagamentoStatus> = pagamentoObservavel

    fun processarPagamento(){

        pagamentoObservavel.value = PagamentoStatus.Processando

        viewModelScope.launch {

            delay(2000)

            pagamentoObservavel.value =
                PagamentoStatus.Sucesso("Pagamento aprovado!")

        }

    }

}