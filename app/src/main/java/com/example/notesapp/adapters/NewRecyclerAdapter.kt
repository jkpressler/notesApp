package com.example.notesapp.adapters

import android.content.Context
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

//added comment for commit test

class NewRecyclerAdapter(val context: Context, list: ArrayList<Note>, ){

    private var listener: OnItemClickListener? = null
    private var arrList = ArrayList<Note>()

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvView1: TextView = itemView.tvTitle
        val tvView2: TextView = itemView.tvBody
        val tvView3: TextView = itemView.tvDateTime
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent, false)
        )
    }

    fun getItemCount() = arrList.size

    fun setData(arrNotesEntList: List<Note>){
        //list = arrNotesEntList as ArrayList<Note>
    }

    fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        holder.tvView1.text = arrList[position].title
        holder.tvView2.text = arrList[position].body
        holder.tvView3.text = arrList[position].dateTime

        //holder.itemView.cardView.setOnClickListener {
        //listener!!.onClicked(arrList[position].id)            //changed noteID parameter of onClicked function to a string... in case things crash
        //}

    }

    interface OnItemClickListener{
        fun onClicked(noteId: String)
    }

    fun onBindViewHolder(holder: RecyclerViewHolder, position: Int, model: Note) {
        TODO("Not yet implemented")
    }


}