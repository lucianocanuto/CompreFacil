package com.lucianocanuto.comprefacil.DI

import com.lucianocanuto.comprefacil.DATA.repository.ProdutosRepositoryImpl
import com.lucianocanuto.comprefacil.DOMAIN.repository.ProdutosRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class ModuloRepository {

    @Binds
    @Singleton
    abstract fun vincularProdutosRepository(
        impl: ProdutosRepositoryImpl
    ): ProdutosRepository


}