package org.kabiri.android.noteroom.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.kabiri.android.noteroom.data.repository.NoteRepository
import org.kabiri.android.noteroom.data.repository.PlayerRepository
import org.kabiri.android.noteroom.model.NoteEntity
import org.kabiri.android.noteroom.model.PlayerEntity
import javax.inject.Inject

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

interface PlayerViewModelAbstract {
    val noteListFlow: Flow<List<PlayerEntity>>
    fun addPlayer(player: PlayerEntity)
    fun updatePlayer(player: PlayerEntity)
    fun deletePlayer(player: PlayerEntity)
}

@HiltViewModel
class PlayerViewModel
@Inject constructor(
    private val playerRepository: PlayerRepository, ): ViewModel(), PlayerViewModelAbstract {
    private val ioScope = CoroutineScope(Dispatchers.IO)

    override val noteListFlow: Flow<List<PlayerEntity>> = playerRepository.getAllFlow()

    override  fun addPlayer(player: PlayerEntity) {
        ioScope.launch {
            playerRepository.insert(player = player)
        }
    }

    override fun updatePlayer(player: PlayerEntity){
        ioScope.launch {
            playerRepository.update(player = player)
        }
    }

    override fun deletePlayer(player: PlayerEntity) {
        ioScope.launch {
            playerRepository.delete(player = player)
        }
    }

}