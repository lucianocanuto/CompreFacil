package com.lucianocanuto.comprefacil.UTIL

sealed class Resource<T> {
    object Carregando : Resource<Nothing>()
    data class Sucesso<T>(val data : T) : Resource<T>()
    data class Erro (val mensagem : String): Resource<Nothing>()
}