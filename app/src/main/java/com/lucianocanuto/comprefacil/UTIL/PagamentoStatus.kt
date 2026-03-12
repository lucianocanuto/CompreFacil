package com.lucianocanuto.comprefacil.UTIL

sealed class PagamentoStatus {

    object Idle : PagamentoStatus()

    object Processando : PagamentoStatus()

    data class Sucesso(
        val mensagem: String
    ) : PagamentoStatus()

    data class Erro(
        val erro: String
    ) : PagamentoStatus()
}