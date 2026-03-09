package com.lucianocanuto.comprefacil.DI

import com.lucianocanuto.comprefacil.DATA.remote.DummyApi
import com.lucianocanuto.comprefacil.DATA.remote.ViaCepAPI
import com.lucianocanuto.comprefacil.DATA.repository.ProdutosRepositoryImpl
import com.lucianocanuto.comprefacil.DOMAIN.repository.ProdutosRepository
import com.lucianocanuto.comprefacil.UTIL.Constantes
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuloHilt {

    @Provides
    @Singleton
    fun proverRetrofit() : Retrofit{ //Aqui é onde faz a conexao com a API
        return Retrofit.Builder()
            .baseUrl(Constantes.DUMMY_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun proverDummyAPI(retrofit: Retrofit): DummyApi{
        return retrofit.create(DummyApi::class.java)

    }


  /*  @Provides
    @Singleton
    fun proverconexaoViaCep(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constantes.VIACEP_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun proverViaCepAPI(retrofit: Retrofit): ViaCepAPI{
        return retrofit.create(ViaCepAPI::class.java)
    }*/


}