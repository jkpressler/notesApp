package com.example.notesapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.notesapp.R
import com.example.notesapp.models.Note
import kotlinx.android.synthetic.main.note_item.view.*
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

//Trying to implement a recyclerview into this app

class RecyclerAdapter(): RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    private var listener: OnItemClickListener? = null
    private var notes = ArrayList<Note>()

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var view : View = itemView

        val tvView1: TextView = view.tvTitle
        val tvView2: TextView = view.tvBody
        val tvView3: TextView = view.tvDateTime
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        Log.i("List", "Count is ${notes.size}")

        return RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent, false)
        )
    }

    override fun getItemCount() = notes.size

    fun setData(noteList: ArrayList<Note>){
        notes = noteList
        Log.i("data set", "data $noteList was set")
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        holder.tvView1.text = notes[position].title
        holder.tvView2.text = notes[position].body
        holder.tvView3.text = notes[position].dateTime

        //holder.itemView.cardView.setOnClickListener {
            //listener!!.onClicked(arrList[position].id)            //changed noteID parameter of onClicked function to a string... in case things crash
        //}

    }

    interface OnItemClickListener{
        fun onClicked(noteId: String)
    }


}