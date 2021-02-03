package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.adapters.NoteAdapter
import com.example.notesapp.models.Note
import com.example.notesapp.models.NoteModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), Observer {
    private var mNoteListAdapter: NoteAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NoteModel


        val dataList: ListView = findViewById(R.id.notesList)
        val data: ArrayList<Note> = ArrayList()
        mNoteListAdapter = NoteAdapter(this, R.layout.note_item, data)
        dataList.adapter = mNoteListAdapter


        makeNote.setOnClickListener{
            addNote()
        }
    }

    private fun addNote(){
        val intent = Intent(this, AddNoteActivity::class.java)
        startActivity(intent)
    }

    override fun update(o: Observable?, arg: Any?) {
        refresh()
    }

    private fun refresh(){
        mNoteListAdapter?.clear()

        val data = NoteModel.getData()
        if(data != null){
            mNoteListAdapter?.clear()
            mNoteListAdapter?.addAll(data)
            mNoteListAdapter?.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        NoteModel.addObserver(this)
        refresh()
    }

    override fun onPause() {
        super.onPause()
        NoteModel.deleteObserver(this)
    }

    override fun onStop() {
        super.onStop()
        NoteModel.deleteObserver(this)
    }

}