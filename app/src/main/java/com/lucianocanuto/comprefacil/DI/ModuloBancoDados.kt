package com.lucianocanuto.comprefacil.DI

import android.content.Context
import androidx.room.Room
import com.lucianocanuto.comprefacil.DATA.RoomDataBase.BancoDados
import com.lucianocanuto.comprefacil.DATA.RoomDataBase.CarrinhoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuloBancoDados {

    @Provides
    @Singleton
    fun proverBancoDados(
        @ApplicationContext context : Context
    ): BancoDados{
        return Room.databaseBuilder(
            context,
            BancoDados::class.java,
            "comprefacil_db"
        ).build()
    }

    @Provides
    fun proverCarrinhoDao(
        db : BancoDados
    ) : CarrinhoDao{
        return db.carrinhoDao()
    }
}