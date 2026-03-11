package com.lucianocanuto.comprefacil.PRESENTATION.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucianocanuto.comprefacil.DATA.repository.EnderecoRepository
import com.lucianocanuto.comprefacil.DOMAIN.model.Endereco
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CadastroViewModel @Inject constructor(
    private val enderecoRepository: EnderecoRepository
) : ViewModel() {

    private val enderecoObservavel = MutableLiveData<Endereco>()
    val endereco: LiveData<Endereco> = enderecoObservavel

    fun buscarCep(cep: String) {

        viewModelScope.launch {

            try {

                val buscarEndereco = enderecoRepository.buscarCep(cep)
                enderecoObservavel.value = buscarEndereco

            } catch (e: Exception) {

            }

        }
    }

}
