package com.example.notesapp.models

import android.media.MediaPlayer
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

object NoteModel: Observable() {

    private var mValueDataListener: ValueEventListener? = null
    private var mNoteList: ArrayList<Note> = ArrayList()

    private fun getDatabaseRef(): DatabaseReference? {
        return FirebaseDatabase.getInstance().reference.child("notes")
    }

    init {
        if (mValueDataListener != null) {
            getDatabaseRef()?.removeEventListener(mValueDataListener!!)
        }
        mValueDataListener = null
        Log.i("NotesModel", "data init line 24")

        mValueDataListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    Log.i("NotesModel", "data updated line 28")
                    val data: ArrayList<Note> = ArrayList()
                    if (snapshot != null) {
                        for (snap: DataSnapshot in snapshot.children) {
                            try {
                                data.add(Note(snap))
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                        mNoteList = data
                        Log.i(
                            "NotesModel",
                            "data updated. there are " + mNoteList!!.size + " notes in the cache and it is $mNoteList"
                        )
                        setChanged()
                        notifyObservers()

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                if (error != null) {
                    Log.i("NotesModel", "data update canceled line 51, err = ${error.message}")
                }

            }

        }
        getDatabaseRef()?.addValueEventListener(mValueDataListener!!)

    }

    fun getData(): ArrayList<Note>{
        return mNoteList
    }

    fun saveNote(n: Note, onCompleteListener: OnCompleteListener<Void>?){
        val reference = getDatabaseRef()?.push()
        reference?.updateChildren(n.toMap())?.addOnCompleteListener{task ->
            if(task.isComplete){
                onCompleteListener?.onComplete(task)
                setChanged()
                notifyObservers()
            }
        }
    }

}