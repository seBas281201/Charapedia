package com.example.charapedia.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.charapedia.data.local.dao.AnimeDAO
import com.example.charapedia.data.local.dao.CharacterDAO
import com.example.charapedia.data.local.dao.CharacterDetailDAO
import com.example.charapedia.data.local.entity.AnimeEntity
import com.example.charapedia.data.local.entity.CharacterDetailEntity
import com.example.charapedia.data.local.entity.CharacterEntity

@Database(
    entities = [CharacterEntity::class, CharacterDetailEntity::class, AnimeEntity::class],
    version = 1,
    exportSchema = false
)


abstract class CharapediaDatabase : RoomDatabase(){

    abstract fun characterDao() : CharacterDAO
    abstract fun characterDetailDao() : CharacterDetailDAO

    abstract fun animeDAO() : AnimeDAO

}