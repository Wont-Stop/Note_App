package com.techmania.cleanarchitecturenoteapp.feature_note.presentation.util

sealed class Screen(val route: String){
    object NotesScreen: Screen("notes_Screen")
    object AddEditNoteScreen: Screen("add_edit_note_Screen")
}