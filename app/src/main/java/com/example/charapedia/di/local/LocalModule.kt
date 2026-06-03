package com.example.charapedia.di.local

import android.content.Context
import androidx.room.Room
import com.example.charapedia.data.local.dao.CharacterDAO
import com.example.charapedia.data.local.database.CharapediaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): CharapediaDatabase {
        return Room.databaseBuilder(
            context,
            CharapediaDatabase::class.java,
            "charapedia_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCharacterDAO(
        db: CharapediaDatabase
    ): CharacterDAO {
        return db.characterDao()
    }

}