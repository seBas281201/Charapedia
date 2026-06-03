package com.example.charapedia.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.charapedia.data.local.dao.CharacterDAO
import com.example.charapedia.data.local.entity.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = false
)


abstract class CharapediaDatabase : RoomDatabase(){

    abstract fun characterDao() : CharacterDAO

}