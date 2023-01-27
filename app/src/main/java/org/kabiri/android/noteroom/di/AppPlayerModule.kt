package org.kabiri.android.noteroom.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.kabiri.android.noteroom.data.AppDatabase
import org.kabiri.android.noteroom.data.AppPlayerDataBase
import org.kabiri.android.noteroom.data.NoteDao
import org.kabiri.android.noteroom.data.PlayerDao
import org.kabiri.android.noteroom.data.repository.NoteRepository
import org.kabiri.android.noteroom.data.repository.PlayerRepository
import javax.inject.Singleton

/**
 *
 * Copyright © 2022 Ali Kabiri
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the “Software”), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

@Module
@InstallIn(SingletonComponent::class)
class AppPlayerModule {

    @Singleton
    @Provides
    fun providePlayerRepository(playerDao: PlayerDao): PlayerRepository {
        return PlayerRepository(playerDao = playerDao)
    }

    @Singleton
    @Provides
    fun provideAppPlayerDataBase(app: Application): AppPlayerDataBase {
        return AppPlayerDataBase.getInstance(app)
    }

    @Singleton
    @Provides
    fun providePlayerDao(appPlayerDatabase: AppPlayerDataBase): PlayerDao {
        return appPlayerDatabase.playerDao()
    }
}