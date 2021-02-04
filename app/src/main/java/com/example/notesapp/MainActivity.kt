package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
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
        setContentView(R.layout.activity_main)                  //set layout as activity_main
        NoteModel


        val dataList: ListView = findViewById(R.id.notesList)
        val data: ArrayList<Note> = ArrayList()                                 //arraylist of notes
        mNoteListAdapter = NoteAdapter(this, R.layout.note_item, data)  //pass in this context, note_item layout, and the arraylist of notes
        dataList.adapter = mNoteListAdapter                                     //add note_items to the listview

        dataList.setOnItemClickListener { parent, view, position, id ->         //listen for a click on a note_item
                Toast.makeText(this, "New note page... need to fix", Toast.LENGTH_LONG).show()


            val element = parent.getItemAtPosition(position) // The item that was clicked
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }

        makeNote.setOnClickListener{
            addNote()                                                           //go to add a note page when "New Note" button is clicked
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