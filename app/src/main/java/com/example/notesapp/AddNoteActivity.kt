package com.example.notesapp

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapp.models.Note
import com.example.notesapp.models.NoteModel
import com.google.android.gms.tasks.OnCompleteListener
import kotlinx.android.synthetic.main.add_note_layout.*

class AddNoteActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_note_layout)

        NoteModel

        addNote.setOnClickListener{saveNote()}
    }

    private fun saveNote(){
        if (add_note_title.text == null || add_note_title.text.length <= 0){
            Toast.makeText(this, "Please add a title", Toast.LENGTH_SHORT).show()
            return
        }
        if (add_note_body.text == null || add_note_body.text.length <= 0){
            Toast.makeText(this, "Please add a note body", Toast.LENGTH_SHORT).show()
            return
        }
        if (add_note_datetime.text == null || add_note_datetime.text.length <= 0) {
            Toast.makeText(this, "Please add a datetime", Toast.LENGTH_SHORT).show()
            return
        }
        val note = Note(null)
        note.title = add_note_title.text.toString()
        note.body = add_note_body.text.toString()
        note.dateTime = add_note_datetime.text.toString()

        NoteModel.saveNote(note, OnCompleteListener { task ->
            if(task.isComplete){
                finish()
            }
        })
    }

}