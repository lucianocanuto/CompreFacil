package com.lucianocanuto.comprefacil.UTIL

sealed class Resource<T> (
    val data : T? = null,
    val mensagem : String? = null
){
    class Sucesso <T> ( data : T ) : Resource<T>(data)
    class Erro <T> ( mensagem : String): Resource<T>( mensagem = mensagem)
    class Carregando <T> : Resource<T>()

}