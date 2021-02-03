package com.example.notesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.models.Note
import kotlinx.android.synthetic.main.note_item.view.*

//added comment for commit test

class RecyclerAdapter() :
    RecyclerView.Adapter<RecyclerAdapter.NotesViewHolder>() {
    var listener: OnItemClickListener? = null
    var arrList = ArrayList<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent)
        )
    }

    override fun getItemCount(): Int {
        return arrList.size
    }

    fun setData(arrNotesEntList: List<Note>){
        arrList = arrNotesEntList as ArrayList<Note>
    }

    fun setOnClickListener(listener1: OnItemClickListener){
        listener = listener1
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        holder.itemView.tvTitle.text = arrList[position].title
        holder.itemView.tvBody.text = arrList[position].body
        holder.itemView.tvDateTime.text = arrList[position].dateTime

        holder.itemView.cardView.setOnClickListener {
            listener!!.onClicked(arrList[position].id!!)            //changed noteID parameter of onClicked function to a string... in case things crash
        }

    }

    class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view){

    }


    interface OnItemClickListener{
        fun onClicked(noteId: String)
    }

}