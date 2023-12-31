package com.techmania.cleanarchitecturenoteapp.feature_note.domain.use_case

import androidx.room.Index
import com.techmania.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.techmania.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import com.techmania.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import com.techmania.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repository: NoteRepository
) {

    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> {
        return repository.getNotes().map { notes ->
            when (noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy { it.timestam }
                        is NoteOrder.Color -> notes.sortedBy { it.color }

                    }

                }

                is OrderType.Descending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedByDescending { it.timestam }
                        is NoteOrder.Color -> notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}