package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.adapters.NoteAdapter
import com.example.notesapp.adapters.RecyclerAdapter
import com.example.notesapp.models.Note
import com.example.notesapp.models.NoteModel
import com.example.notesapp.models.NoteModel.getData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_note_layout.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), Observer{
    private var mNoteListAdapter: NoteAdapter? = null
    //private lateinit var rAdapter: RecyclerAdapter
    //private lateinit var linearLayoutManager: LinearLayoutManager
    //private val data: ArrayList<Note> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)                  //set layout as activity_main
        NoteModel

        Log.i("noteModel", "${getData()}")

        //linearLayoutManager = LinearLayoutManager(this)
        //rvNotesList.layoutManager = linearLayoutManager


        val dataList: ListView = findViewById(R.id.notesList)  //arraylist of notes
        val data: ArrayList<Note> = ArrayList()
        mNoteListAdapter = NoteAdapter(this, R.layout.note_item, data)  //pass in this context, note_item layout, and the arraylist of notes
        dataList.adapter = mNoteListAdapter                                     //add note_items to the listview

        //rAdapter = RecyclerAdapter()
        if (mNoteListAdapter!=null){
            Log.i("rAdapter","It made it here and data is $data")
        }
        //rAdapter.setData(data)
        //rvNotesList.adapter = rAdapter

        dataList.setOnItemClickListener { parent, view, position, id ->         //listen for a click on a note_item
                Toast.makeText(this, "New note page... need to fix", Toast.LENGTH_SHORT).show()

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

    private fun refresh() {
        mNoteListAdapter?.clear()

        val data = getData()
        if (data != null) {
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