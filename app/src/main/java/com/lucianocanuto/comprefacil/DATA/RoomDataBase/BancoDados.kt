package com.lucianocanuto.comprefacil.DATA.RoomDataBase

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [CarrinhoItem::class],
    version = 1
)
abstract class BancoDados : RoomDatabase() {

    abstract fun carrinhoDao(): CarrinhoDao
}