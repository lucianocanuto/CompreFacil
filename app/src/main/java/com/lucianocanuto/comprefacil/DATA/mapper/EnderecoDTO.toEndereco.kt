package com.lucianocanuto.comprefacil.DATA.mapper

import com.lucianocanuto.comprefacil.DATA.dto.viacep.EnderecoDTO
import com.lucianocanuto.comprefacil.DOMAIN.model.Endereco

fun EnderecoDTO.toEndereco(): Endereco{
     return Endereco(
         cep = cep,
         logradouro = logradouro,
         bairro = bairro,
         localidade = localidade,
         uf = uf
     )
}