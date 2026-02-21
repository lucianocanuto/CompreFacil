package com.lucianocanuto.comprefacil.DATA.mapper

import com.lucianocanuto.comprefacil.DATA.dto.Product
import com.lucianocanuto.comprefacil.DOMAIN.model.Produto

fun Product.toDomain(): Produto {
    return Produto(
        id = id,
        titulo = title,
        preco = price,
        imagem = thumbnail
    )
}