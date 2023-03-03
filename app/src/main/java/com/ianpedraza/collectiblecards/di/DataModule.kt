package com.ianpedraza.collectiblecards.di

import com.ianpedraza.collectiblecards.data.datasources.CardsDataSource
import com.ianpedraza.collectiblecards.data.repositories.CardsRepository
import com.ianpedraza.collectiblecards.data.repositories.DefaultCardsRepository
import com.ianpedraza.collectiblecards.framework.CardsLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun provideLocalCardsDataSource(
        cardsDataSource: CardsLocalDataSource,
    ): CardsDataSource

    @Singleton
    @Binds
    abstract fun provideCardsRepository(
        cardsRepository: DefaultCardsRepository,
    ): CardsRepository

    companion object {
        @Singleton
        @Provides
        fun provideCardsLocalDataSource(): CardsLocalDataSource = CardsLocalDataSource
    }
}
